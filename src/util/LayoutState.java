package util;

import java.awt.*;
import java.util.ArrayList;

/**
 */
public final class LayoutState {
    private final Component[] myComponents;
    private final GridConstraints[] myConstraints;
    private final int myColumnCount;
    private final int myRowCount;
    final Dimension[] myPreferredSizes;
    final Dimension[] myMinimumSizes;

    public LayoutState(final GridLayoutManager layout, final boolean ignoreInvisibleComponents) {
        // collect all visible components
        final ArrayList componentsList = new ArrayList(layout.getComponentCount());
        final ArrayList constraintsList = new ArrayList(layout.getComponentCount());
        for (int i=0; i < layout.getComponentCount(); i++){
            final Component component = layout.getComponent(i);
            if (!ignoreInvisibleComponents || component.isVisible()) {
                componentsList.add(component);
                final GridConstraints constraints = layout.getConstraints(i);
                constraintsList.add(constraints);
            }
        }

        myComponents = (Component[])componentsList.toArray(new Component[0]);
        myConstraints = (GridConstraints[])constraintsList.toArray(GridConstraints.EMPTY_ARRAY);

        myMinimumSizes = new Dimension[myComponents.length];
        myPreferredSizes = new Dimension[myComponents.length];

        myColumnCount = layout.getColumnCount();
        myRowCount = layout.getRowCount();
    }

    public int getComponentCount(){
        return myComponents.length;
    }

    public Component getComponent(final int index){
        return myComponents[index];
    }

    public GridConstraints getConstraints(final int index){
        return myConstraints[index];
    }

    public int getColumnCount(){
        return myColumnCount;
    }

    public int getRowCount(){
        return myRowCount;
    }
}