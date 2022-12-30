package util;

final class HorizontalInfo extends DimensionInfo{
    HorizontalInfo(final LayoutState layoutState, final int gap){
        super(layoutState, gap);
    }

    protected int getOriginalCell(final GridConstraints constraints){
        return constraints.getColumn();
    }

    protected int getOriginalSpan(final GridConstraints constraints){
        return constraints.getColSpan();
    }

    int getSizePolicy(final int componentIndex){
        return myLayoutState.getConstraints(componentIndex).getHSizePolicy();
    }

    int getChildLayoutCellCount(final GridLayoutManager childLayout) {
        return childLayout.getColumnCount();
    }

    public int getMinimumWidth(final int componentIndex){
        return getMinimumSize(componentIndex).width;
    }

    public DimensionInfo getDimensionInfo(GridLayoutManager grid) {
        return grid.myHorizontalInfo;
    }

    public int getCellCount(){
        return myLayoutState.getColumnCount();
    }

    public int getPreferredWidth(final int componentIndex){
        return getPreferredSize(componentIndex).width;
    }
}