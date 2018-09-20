package com.exasol.util.visitor;

import java.util.Collection;

/**
 * This in the interface for visitors that allow plugging in sub-visitors.
 */
public interface HierarchicalVisitor {
    /**
     * Register a sub-visitor
     *
     * @param child sub-visitor to be registered
     */
    public void register(HierarchicalVisitor child, Class<? extends Visitable> responsibleFor);

    /**
     * Get a list of registered sub-visitors
     *
     * @return list of registered sub-visitors
     */
    public Collection<HierarchicalVisitor> getRegisted();

    HierarchicalVisitor getRegisteredVisitorForType(final Visitable host);

    /**
     * Fallback methods that allows delegation of visiting to registered visitors.
     *
     * @param host object requesting the visit
     */
    public void visit(Visitable host);
}