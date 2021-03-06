/**
 * Copyright (c) 2015 Greg Wright
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 3 only, as
 * published by the Free Software Foundation.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 3 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 * 
 * @author yesimwearingpants
 * Created Jul 2, 2015
 */
package com.sww.voxel.draw;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import com.sww.voxel.draw.texture.TextureLoader;

public class Loader {

	private List<Integer> vaos = new ArrayList<>();
	private List<Integer> vbos = new ArrayList<>();
	private List<Integer> texz = new ArrayList<>();

	/**
	 * Load to vao.
	 *
	 * @param positions the positions
	 * @param indices the indices
	 * @return the model
	 */
	public RawModel loadToVAO(float[] positions, float[] texCoords, int[] indices) {
		int vaoID = createVAO();
		bindIndicesBuffer(indices);
		storeDataInAttribList(0,3, positions);
		storeDataInAttribList(0,2, texCoords);
		unbindVAO();
		return new RawModel(vaoID, indices.length);
	}

	public int loadTexture(String fileName) {
		TextureLoader texture = new TextureLoader(Paths.get(String.format(/*Main.args[2]*/
			"assets/textures/%s", fileName)));
		int textureID = texture.getID();
		texz.add(textureID);
		return textureID;
	}

	/**
	 * Creates the vao.
	 *
	 * @return the int
	 */
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vaos.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}

	/**
	 * Store data in attribute list.
	 *
	 * @param attribNum the attrib num
	 * @param positions the positions
	 */
	private void storeDataInAttribList(int attribNum, int coordSize, float[] positions) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeDataInFloatBuffer(positions);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attribNum, coordSize, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}

	/**
	 * Store data in float buffer.
	 *
	 * @param data the data
	 * @return the float buffer
	 */
	private FloatBuffer storeDataInFloatBuffer(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	/**
	 * Bind indices buffer.
	 *
	 * @param indices the indices
	 */
	private void bindIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbos.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = storeDataInFloatBuffer(indices);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
	}

	/**
	 * Store data in float buffer.
	 *
	 * @param data the data
	 * @return the int buffer
	 */
	private IntBuffer storeDataInFloatBuffer(int[] data) {
		IntBuffer buffer = BufferUtils.createIntBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}

	/**
	 * Unbind vao.
	 */
	private void unbindVAO() {
		GL30.glBindVertexArray(0);
	}

	/**
	 * Clean up.
	 */
	public void cleanUp() {
		for(int vao:vaos) {
			GL30.glDeleteVertexArrays(vao);
		}
		for(int vbo:vbos) {
			GL15.glDeleteBuffers(vbo);
		}
		for(int tex:texz) {
			GL11.glDeleteTextures(tex);
		}
	}

}
