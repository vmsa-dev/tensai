/*
 * This file is part of tensai, licensed under the MIT License (MIT).
 *
 * Copyright (c) 2022 PhoMC
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

package dev.phomc.tensai.fabric;

import net.fabricmc.api.DedicatedServerModInitializer;
import net.fabricmc.fabric.api.entity.event.v1.ServerPlayerEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import dev.phomc.tensai.fabric.clients.FabricClientHandle;
import dev.phomc.tensai.fabric.event.listeners.PlayerJoinListener;
import dev.phomc.tensai.fabric.keybinding.KeyBindingMessageSubscriber;
import dev.phomc.tensai.networking.Channel;

public class TensaiFabricServer implements DedicatedServerModInitializer {
	@Override
	public void onInitializeServer() {
		new KeyBindingMessageSubscriber(Channel.KEYBINDING).onInitialize();

		ServerPlayerEvents.COPY_FROM.register((oldPlayer, newPlayer, alive) -> {
			((FabricClientHandle) oldPlayer).transferTo(newPlayer);
		});

		ServerPlayConnectionEvents.JOIN.register(new PlayerJoinListener());
	}
}
