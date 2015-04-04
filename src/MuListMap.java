import structures.MapI;

import java.util.Iterator;
import java.util.LinkedList;

public class MuListMap<K,V> implements MapI<K,V> {

	protected LinkedList<Entry<K,V>> $store;

	public MuListMap(){
		$store = new LinkedList<MapI.Entry<K,V>>();
	}

	/**
	 * Get iterator.
	 *
	 * @return iterator
	 */
	@Override
	public Iterator<Entry<K,V>> iterator(){
		return $store.iterator();
	}

	/**
	 * Determine whether map contains key.
	 *
	 * @param key to find
	 *
	 * @return whether key was found
	 */
	@Override
	public boolean contains(K key) {
		for (Entry<K, V> entry: $store) {
			if (entry.getKey().equals(key)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Retrieve a value by its key.
	 *
	 * @param key to use
	 *
	 * @return value associated with key
	 */
	@Override
	public V get(K key) {
		for (Entry<K, V> entry: $store) {
			if (entry.getKey().equals(key)) {
				return entry.getValue();
			}
		}
		return null;
	}

	/**
	 * Determine number of entries.
	 *
	 * @return number of entries
	 */
	@Override
	public int size() {
		return $store.size();
	}

	/**
	 * Remove entry by key.
	 *
	 * @param key to use
	 *
	 * @return whether entry was removed
	 */
	@Override
	public boolean remove(K key) {
		Iterator<Entry<K, V>> iterator = $store.iterator();
		while (iterator.hasNext()) {
			if (iterator.next().getKey().equals(key)) {
				iterator.remove();
				return true;
			}
		}
		return false;
	}

	/**
	 * Set or add an entry.
	 *
	 * @param key to use
	 * @param value to set
	 *
	 * @return whether a new entry was added
	 */
	@Override
	public boolean set(K key, V value) {
		Pair<Entry<K, V>, Boolean> entrypair = getEntry(key);
		entrypair.left.setValue(value);
		return entrypair.right;
	}

	protected Pair<Entry<K, V>, Boolean> getEntry(K key) {
		for (Entry<K, V> entry: $store) {
			if (entry.getKey().equals(key)) {
				return new Pair<Entry<K, V>, Boolean>(entry, false);
			}
		}
		Entry<K, V> entry = new ListEntry<K, V>(key);
		$store.add(entry);
		return new Pair<Entry<K, V>, Boolean>(entry, true);
	}

	/**
	 * Convert map to array of entries.
	 *
	 * @return array of entries
	 */
	@Override
	public Entry<K, V>[] toArray() {
		Object[] raw = $store.toArray();
		Entry<K, V>[] array = new Entry[raw.length];
		System.arraycopy(raw, 0, array, 0, raw.length);
		return array;
	}

	protected static class ListEntry<K, V> implements Entry<K, V>{

		protected K $key;
		protected V $value;

		public ListEntry(K key) {
			$key = key;
		}

		public ListEntry(K key, V value) {
			this(key);
			$value = value;
		}

		@Override
		public void setValue(V value) {
			$value = value;
		}

		@Override
		public V getValue() {
			return $value;
		}

		@Override
		public K getKey() {
			return $key;
		}

		@Override
		public boolean equals(Object o) {
			if (o instanceof Entry) {
				Entry<K, V> entry = (Entry<K, V>)(o);
				return entry.getKey().equals(getKey()) && entry.getValue().equals(getValue());
			}
			return false;
		}
	}

	protected static class Pair<A, B> {
		public A left;
		public B right;
		public Pair(A a, B b) {
			left = a;
			right = b;
		}
	}

}