/*
 * Java Genetic Algorithm Library (@__identifier__@).
 * Copyright (c) @__year__@ Franz Wilhelmstötter
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA  02110-1301  USA
 *
 * Author:
 *     Franz Wilhelmstötter (franz.wilhelmstoetter@gmx.at)
 *
 */
package org.jenetics.util;

import java.util.Random;

/**
 * @author <a href="mailto:franz.wilhelmstoetter@gmx.at">Franz Wilhelmstötter</a>
 * @since 1.1
 * @version 1.1 &mdash; <em>$Date$</em>
 */
abstract class Random64 extends Random {

	private static final long serialVersionUID = 1L;

	public Random64() {
	}

	public Random64(long seed) {
		super(seed);
	}

	/**
	 * Optimized version of the {@link Random#nextBytes(byte[])} method for 64-bit
	 * random engines.
	 */
	@Override
	public void nextBytes(final byte[] bytes) {
		for (int i = 0, len = bytes.length; i < len;) {
			int n = Math.min(len - i, Long.SIZE/Byte.SIZE);
			for (long rnd = nextLong(); n-- > 0; rnd >>= Byte.SIZE) {
				bytes[i++] = (byte)rnd;
			}
		}
	}

	/**
	 * Optimized version of the {@link Random#nextDouble()} method for 64-bit
	 * random engines.
	 */
	@Override
	public double nextDouble() {
		return toDouble(nextLong());
		//final long x = nextLong();
		//return (((x >>> 38) << 27) + (((int)x) >>> 5))/(double)(1L << 53);
	}

	static double toDouble(final long x) {
		return (((x >>> 38) << 27) + (((int)x) >>> 5))/(double)(1L << 53);
	}

	static double toDouble2(final long x) {
		int a = (int)(x >>> 32);
		int b = (int)x;

		a >>>= 32 - 26;
		b >>>= 32 - 27;

		return (((long)a << 27) + b) / (double)(1L << 53);
	}

    public double nextDou() {
    	//((long)(next(32)) << 32) + next(32);

    	// next(26) -> a >>> (32 - 26)

return (((long)(next(26)) << 27) + next(27)) / (double)(1L << 53);
    }




}
