package ru.denivip.android.video;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import android.media.MediaPlayer;
import android.media.Metadata;

/**
 * Этот класс содержит обертки для вызова внутренних методов MediaPlayer,
 * которые недоступны через публичный API.
 * 
 * @hide
 */
public class MediaPlayerInternals {
	public static Metadata getMetadata(MediaPlayer mp,
			final boolean update_only, final boolean apply_filter) {
		try {
			Method method = mp.getClass().getMethod("getMetadata");
			return (Metadata) method.invoke(mp, update_only, apply_filter);
		} catch (NoSuchMethodException e) {
			return null;
		} catch (IllegalAccessException e) {
			return null;
		} catch (InvocationTargetException e) {
			return null;
		}
	}

	public static boolean suspend(MediaPlayer mp) {
		try {
			Method method = mp.getClass().getMethod("suspend");
			return ((Boolean)method.invoke(mp)).booleanValue();
		} catch (NoSuchMethodException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		}
	}

	public static boolean resume(MediaPlayer mp) {
		try {
			Method method = mp.getClass().getMethod("resume");
			return ((Boolean)method.invoke(mp)).booleanValue();
		} catch (NoSuchMethodException e) {
			return false;
		} catch (IllegalAccessException e) {
			return false;
		} catch (InvocationTargetException e) {
			return false;
		}
	}
}
