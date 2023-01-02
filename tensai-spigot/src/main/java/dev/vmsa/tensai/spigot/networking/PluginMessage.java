/*
 * This file is part of tensai, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2022 VMSA
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package dev.vmsa.tensai.spigot.networking;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import com.google.common.io.ByteArrayDataOutput;
import com.google.common.io.ByteStreams;

/**
 * <p><b>Message format: </b>{@code [Message Type: UTF-8][Contents...]}.</p>
 *
 */
public abstract class PluginMessage {
	public static final String VFX_CHANNEL = "tensai:vfx";

	public final String channel;
	public final String messageType;

	public PluginMessage(String channel, String messageType) {
		this.channel = channel;
		this.messageType = messageType;
	}

	public void write(DataOutput stream) throws IOException { }
	public void read(DataInput stream) throws IOException { }

	public byte[] createBytes() {
		ByteArrayDataOutput stream = ByteStreams.newDataOutput();

		try {
			stream.writeUTF(messageType);
			write(stream);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

		return stream.toByteArray();
	}
}
