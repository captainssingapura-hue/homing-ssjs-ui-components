// =============================================================================
// PaneTabs — a tab's two parts, for the pane that holds it: the CHIP on the
// strip and the PANEL in the content. Made from the tab on a branch of the
// tab's own, placed where the pane's order says, taken out again, and named.
// Static, over the pane it is handed, as PaneMenus and PaneKeys are: the pane
// keeps the order and the state; these put a tab's parts where the state says.
//
//   PaneTabs.build(pane, tab, onMenu)   → the entry { id, tab, pinned, widget, chip, panel,
//                                         branch, menu }: the chip minted on the tab's own
//                                         branch, the widget's root in the panel. onMenu is
//                                         the pane's opener for the chip's menu, or null
//   PaneTabs.place(pane, entry, index)  → the index it took: clamped to the pinned block or
//                                         after it; its panel in the content, the widget's
//                                         membership under the pane; the strip follows
//   PaneTabs.takeOut(pane, i)           → the entry: its chip and panel out; the strip follows
//   PaneTabs.take(pane, tp, index, menu) → the index: a tab-pane's own chip and pane placed as they
//                                         are, hidden until shown, its host set; nothing minted
//   PaneTabs.letGo(pane, tp)            → the index it left, or −1 when it was not here; its
//                                         host unset; nothing dissolved
//   PaneTabs.chips(pane)                → the chips, in the pane's order
//   PaneTabs.retitle(pane, id, title)   the tab's name, on the tab and on its chip
//   PaneTabs.reicon(pane, id, icon?)    the tab's icon — the holder's element — on the tab
//                                       and on its chip; none takes it away
//
// A tab's name and icon are its HOLDER'S to give and the pane's to show: the
// pane never makes one up, and a holder changes them by call, whenever it
// likes. Neither is the arrangement, so neither is reported.
// =============================================================================

const _tabsOwner = Object.freeze({ toString: () => "multiTabPane" });

class PaneTabs {

    static build(pane, tab, onMenu) {
        var own = pane._branch.createBranch("tab-" + tab.id.replace(/[^A-Za-z0-9_-]/g, "_"));
        own.activate(_tabsOwner);
        var handlers = { onSelect: function () { pane.switchTab(tab.id); }, onClose: function () { pane.removeTab(tab.id); } };
        handlers.onMenu = onMenu || null;
        var chip = pane._strip.chip(tab, handlers, own);
        var panel = own.createElement("panel", "div");
        css.addClass(panel, mtp_tab_content, mtp_tab_content_hidden);
        panel.setAttribute("role", "tabpanel");
        panel.appendChild(tab.widget.root);
        return { id: tab.id, tab: tab, pinned: !!tab.pinned, widget: tab.widget, chip: chip, panel: panel, branch: own, menu: handlers.onMenu || null };
    }

    static place(pane, entry, index) {
        var lo = entry.pinned ? 0 : pane._pinnedCount();
        var hi = entry.pinned ? pane._pinnedCount() : pane._tabs.length;
        if (index == null || index > hi) index = hi;
        if (index < lo) index = lo;
        if (entry.widget.focus.in !== pane.focus) pane.focus.adopt(entry.widget.focus);   // placement follows the rendered UI; first, so a refusal changes nothing
        pane._tabs.splice(index, 0, entry);
        pane._content.appendChild(entry.panel);
        pane._refresh();
        return index;
    }

    static take(pane, tp, index, menu) {
        if (tp.host()) throw new Error("[MultiTabPane] tab-pane '" + tp.id + "' is held by another host: it is let go there first");
        tp.shown(false);
        pane._strip.adopt(tp.chip, tp.pinned);
        var at = PaneTabs.place(pane, { id: tp.id, tab: tp, tabPane: tp, pinned: tp.pinned, widget: tp.widget, chip: tp.chip, panel: tp.pane, branch: null, menu: menu }, index);
        tp._hostedBy(pane);
        return at;
    }

    static letGo(pane, tp) {
        var i = pane._find(tp.id);
        if (i < 0 || pane._tabs[i].tabPane !== tp) return -1;
        PaneTabs.takeOut(pane, i);
        tp._hostedBy(null);
        return i;
    }

    static takeOut(pane, i) {
        var entry = pane._tabs[i];
        pane._tabs.splice(i, 1);
        pane._strip.remove(entry.chip);
        pane._content.removeChild(entry.panel);
        pane._refresh();
        if (pane._activeId === entry.id) pane._activeId = null;
        return entry;
    }

    static chips(pane) {
        var out = [];
        for (var i = 0; i < pane._tabs.length; i++) out.push(pane._tabs[i].chip);
        return out;
    }

    static retitle(pane, id, title) {
        var entry = pane._tabs[pane._require(id)];
        if (entry.tabPane) { entry.tabPane.title(title); return; }   // a tab-pane's name is its own
        entry.tab.title = title;
        pane._strip.retitle(entry.chip, title);
    }

    static reicon(pane, id, icon) {
        var entry = pane._tabs[pane._require(id)];
        if (entry.tabPane) { entry.tabPane.icon(icon || null); return; }
        entry.tab.icon = icon || null;
        pane._strip.reicon(entry.chip, entry.tab.icon);
    }
}
