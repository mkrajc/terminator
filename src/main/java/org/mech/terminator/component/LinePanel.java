package org.mech.terminator.component;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public abstract class LinePanel extends Panel {

	public LinePanel() {}

	public LinePanel(final Dimension size) {
		super(size);
	}
	
	@Override
	public void doPropagateTerminal(final ITerminal terminal) {
		super.doPropagateTerminal(terminal);
		
		for (int i = 0; i < getComponentCount(); i++) {
			final Component component = components.get(i);
			component.propagateTerminal(terminal);
		}
	}

	@Override
	protected final void doLayout() {
		final Map<Component, PreferedToNormal> componentCached = new LinkedHashMap<Component, LinePanel.PreferedToNormal>();

		for (int i = 0; i < getComponentCount(); i++) {

			final Component cw = components.get(i);

			final PreferedToNormal ptn = new PreferedToNormal();
			ptn.prefered = getPreferedSize(cw);

			componentCached.put(cw, ptn);
		}

		countSize(componentCached.values(), getLimitSize());

		int lastPosition = 0;

		for (int i = 0; i < getComponentCount(); i++) {
			final Component cw = components.get(i);
			final PreferedToNormal preferedToNormal = componentCached.get(cw);
			cw.rectangle = new Rectangle(getPosition(lastPosition), getDimension(preferedToNormal.normal));
			lastPosition += preferedToNormal.normal;
		}
	}

	protected abstract Position getPosition(int lastPosition);

	protected abstract Dimension getDimension(int normal);

	protected abstract int getLimitSize();

	protected abstract Integer getPreferedSize(Component cw);

	protected void countSize(final Collection<PreferedToNormal> sizes, final int limitSize) {

		final List<PreferedToNormal> prefered = new ArrayList<LinePanel.PreferedToNormal>();
		final List<PreferedToNormal> dynamic = new ArrayList<LinePanel.PreferedToNormal>();

		for (final PreferedToNormal i : sizes) {
			if (i.prefered != null) {
				prefered.add(i);
			} else {
				dynamic.add(i);
			}
		}

		if (prefered.size() == sizes.size()) {
			countOnlyPrefered(prefered, limitSize);
		} else if (prefered.size() == 0) {
			countOnlyDynamic(dynamic, limitSize);
		} else {
			countMixed(prefered, dynamic, limitSize);
		}
	}

	private void countMixed(final List<PreferedToNormal> prefered, final List<PreferedToNormal> dynamic, final int limit) {
		final int preferedSizeTotal = countOnlyPrefered(prefered, limit);
		final int nextLimit = limit - preferedSizeTotal;
		countOnlyDynamic(dynamic, nextLimit);
	}

	private void countOnlyDynamic(final List<PreferedToNormal> sizes, final int limit) {
		final int compSize = sizes.size();
		final int ratio = limit / compSize;

		int rest = limit;
		for (int i = 0; i < compSize; i++) {
			final PreferedToNormal ptn = sizes.get(i);
			final int newSize = (i == compSize - 1) ? rest : ratio;

			if (newSize <= 0) {
				throw new IllegalArgumentException("There is not space left for dynamic components");
			}

			ptn.normal = newSize;

			rest -= ratio;
		}
	}

	private int countOnlyPrefered(final List<PreferedToNormal> sizes, final int limitSize) {
		int sum = 0;

		for (final PreferedToNormal i : sizes) {
			i.normal = i.prefered;
			sum += i.normal;
		}

		handlePreferredLimit(sum, limitSize);

		return sum;

	}

	private void handlePreferredLimit(final int sum, final int limitSize) {
		if (sum > limitSize) {
			throw new IllegalArgumentException("Preffered size overlap limit. This case not yet implemented");
		}
	}

	private static class PreferedToNormal {
		Integer prefered;
		Integer normal;

		@Override
		public String toString() {
			return "prf=" + prefered + ", nrml=" + normal;
		}
	}

}
