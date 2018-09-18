package com.exasol.sql;

public interface Fragment {
    @Override
    public String toString();

    public Fragment getParent();

    public void accept(FragmentVisitor visitor);

    public Fragment getRoot();

    public boolean isFirstSibling();

    /**
     * Get child at index position
     * 
     * @param index position of the child
     * @return child at index
     * @throws IndexOutOfBoundsException if the index is out of range (index < 0 ||
     *                                   index >= size())
     */
    public Fragment getChild(int index) throws IndexOutOfBoundsException;
}
