package util;

final class VerticalInfo extends DimensionInfo {
    VerticalInfo(final LayoutState layoutState, final int gap){
        super(layoutState, gap);
    }

    protected int getOriginalCell(final GridConstraints constraints){
        return constraints.getRow();
    }

    protected int getOriginalSpan(final GridConstraints constraints){
        return constraints.getRowSpan();
    }

    int getSizePolicy(final int componentIndex){
        return myLayoutState.getConstraints(componentIndex).getVSizePolicy();
    }

    int getChildLayoutCellCount(final GridLayoutManager childLayout) {
        return childLayout.getRowCount();
    }

    public int getMinimumWidth(final int componentIndex){
        return getMinimumSize(componentIndex).height;
    }

    public DimensionInfo getDimensionInfo(GridLayoutManager grid) {
        return grid.myVerticalInfo;
    }

    public int getCellCount(){
        return myLayoutState.getRowCount();
    }

    public int getPreferredWidth(final int componentIndex){
        return getPreferredSize(componentIndex).height;
    }
}