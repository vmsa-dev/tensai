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

package dev.vmsa.tensai.spigot;

import java.io.IOException;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import dev.vmsa.tensai.Tensai;

public class TensaiSpigot extends JavaPlugin {
	private TensaiSpigotInstance instance;
	protected Logger logger;

	@Override
	public void onEnable() {
		logger = getLogger();
		Tensai.createInstance(() -> instance = new TensaiSpigotInstance(this));
		Tensai.setInstanceGetter(server -> getServer() == server? instance : null);
	}

	@Override
	public void onDisable() {
		try {
			instance.close();
		} catch (IOException e) {
			logger.severe("Failed to close TensaiSpigotInstance: An exception thrown");
			e.printStackTrace();
			logger.info("Please create a new issue in https://github.com/vmsa-dev/tensai/issues");
			logger.info("Additionally, if you are using /reload, please restart the server to avoid memory leaks.");
		}
	}
}
