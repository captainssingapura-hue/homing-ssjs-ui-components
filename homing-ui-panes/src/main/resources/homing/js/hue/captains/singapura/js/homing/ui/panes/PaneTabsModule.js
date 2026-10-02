// =============================================================================
// PaneTabs — a tab-pane's two parts, for the pane that holds it: the CHIP on
// the strip and the PANE in the content, the tab-pane's own. Placed where the
// pane's order says, taken out again, and named. Static, over the pane it is
// handed, as PaneMenus and PaneKeys are: the pane keeps the order and the
// state; these put a tab-pane's parts where the state says.
//
//   PaneTabs.place(pane, entry, index)  → the index it took: clamped to the pinned block or
//                                         after it; its panel in the content, the widget's
//                                         membership under the pane; the strip follows
//   PaneTabs.takeOut(pane, i)           → the entry: its chip and panel out; the strip follows
//   PaneTabs.take(pane, tp, index, menu) → the index: a tab-pane's own chip and pane placed as they
//                                         are, hidden until shown, its host set; nothing minted
//   PaneTabs.letGo(pane, tp)            → the index it left, or −1 when it was not here; its
//                                         host unset; nothing dissolved; a close reported
//                                         (TabRemoved), a neighbour shown, an emptied pane told
//   PaneTabs.admits(pane, tp)           → whether the pane would take it, were it let go where
//                                         it is: the law, the id, the member's name
//   PaneTabs.menu(pane, tp, at, byKey)  → its chip's menu, while it is here; true when one opened
//   PaneTabs.chips(pane)                → the chips, in the pane's order
//   PaneTabs.retitle(pane, id, title)   the tab's name, said to the tab-pane, whose chip shows it
//   PaneTabs.reicon(pane, id, icon?)    the tab's icon — the holder's element — likewise; none
//                                       takes it away
//
// A tab's name and icon are the tab-pane's, its HOLDER'S to give and the
// pane's to show: the pane never makes one up, and a holder changes them by
// call, whenever it likes. Neither is the arrangement, so neither is reported.
// =============================================================================

class PaneTabs {

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
        pane._validate(tp);
        if (tp.host()) throw new Error("[MultiTabPane] tab-pane '" + tp.id + "' is held by another host: it is let go there first");
        tp.shown(false);
        pane._strip.adopt(tp.chip, tp.pinned);
        var at = PaneTabs.place(pane, { id: tp.id, tab: tp, tabPane: tp, pinned: tp.pinned, get widget() { return tp.widget; },   // live: a replace is seen at once
                                        chip: tp.chip, panel: tp.pane, branch: null, menu: menu }, index);
        tp._hostedBy(pane);
        return at;
    }

    static letGo(pane, tp) {
        var i = pane._find(tp.id);
        if (i < 0 || pane._tabs[i].tabPane !== tp) return -1;
        PaneTabs.takeOut(pane, i);
        tp._hostedBy(null);
        if (tp.closed()) pane._fire(PaneEvents.TabRemoved(pane.slotId, tp, i));   // a close is said where it was; a move is the desk's
        pane._activateNeighbour(i);
        pane._left();
        return i;
    }

    static admits(pane, tp) {
        try { pane._validate(tp); } catch (e) { return false; }
        return !!tp.chip && tp.host() !== pane;
    }

    static menu(pane, tp, at, byKey) {
        var i = pane._find(tp.id);
        return i >= 0 && pane._tabs[i].menu ? pane._tabs[i].menu(at, byKey) : false;
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

    static retitle(pane, id, title) { pane._tabs[pane._require(id)].tabPane.title(title); }

    static reicon(pane, id, icon) { pane._tabs[pane._require(id)].tabPane.icon(icon || null); }
}
