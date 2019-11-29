package prjs.project.lampshade.api.javafx;

import java.util.function.Consumer;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.WeakInvalidationListener;

public final class BindTools {
	public static class Action<O extends Observable> {
		private final O[] observables;
		private final InvalidationListener[] listeners;

		@SuppressWarnings("unchecked")
		@SafeVarargs
		private Action(final Consumer<O> action, final O... observables) {
			this.observables = observables.clone();
			listeners = new InvalidationListener[observables.length];
			for (int i = 0; i < observables.length; i++)
				this.observables[i].addListener(listeners[i] = observable -> action.accept((O) observable));
		}

		public void unbind() {
			for (int i = 0; i < observables.length; i++)
				observables[i].removeListener(listeners[i]);
		}

	}

	@SafeVarargs
	public static <O extends Observable> Action<O> bindStrong(final Consumer<O> action, final O... observables) {
		return new Action<>(action, observables);
	}

	/**
	 * Binds to the given {@link Observable}s, so that <code>action</code> is
	 * executed each time any {@link Observable} is invalidated (i.e., it has
	 * changed). A {@link WeakInvalidationListener} is used to listen to changes on
	 * the {@link Observable}s. The {@link Observable} that changed is passed to the
	 * <code>action</code> specified, whenever the <code>action</code> is invoked
	 * due to the {@link Observable}'s changing.
	 *
	 * @param action      The action to execute each time an {@link Observable} is
	 *                    invalidated.
	 * @param observables The {@link Observable}s that will be listened for changes.
	 * @param <O>         The supertype of the {@link Observable}s.
	 */
	@SuppressWarnings("unchecked")
	@SafeVarargs
	public static <O extends Observable> void bindWeak(final Consumer<O> action, final O... observables) {
		for (final O o : observables)
			o.addListener(new WeakInvalidationListener(observable -> action.accept((O) observable)));
	}

	private BindTools() {
	}
}
