package com.exasol.util.visitor;

import java.util.*;

/**
 * Abstract base class for building visitors that are organized in a hierarchy.
 */
public abstract class AbstractHierarchicalVisitor implements HierarchicalVisitor {
    private final Map<Class<? extends Visitable>, HierarchicalVisitor> registeredVisitors = new HashMap<>();

    @Override
    public void register(final HierarchicalVisitor visitor, final Class<? extends Visitable> responsibleFor) {
        this.registeredVisitors.put(responsibleFor, visitor);
    }

    @Override
    public Collection<HierarchicalVisitor> getRegisted() {
        return this.registeredVisitors.values();
    }

    @Override
    public HierarchicalVisitor getRegisteredVisitorForType(final Visitable host) {
        return this.registeredVisitors.get(host.getClass());
    }
}
