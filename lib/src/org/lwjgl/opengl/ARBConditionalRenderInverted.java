/*
 * Copyright LWJGL. All rights reserved.
 * License terms: http://lwjgl.org/license.php
 * MACHINE GENERATED FILE, DO NOT EDIT
 */
package org.lwjgl.opengl;

/**
 * Native bindings to the <a href="http://www.opengl.org/registry/specs/ARB/conditional_render_inverted.txt">ARB_conditional_render_inverted</a> extension.
 * 
 * <p>This extension adds new modes to {@link GL30#glBeginConditionalRender BeginConditionalRender} which invert the condition used to determine whether to draw or not.</p>
 * 
 * <p>Requires {@link GL30 OpenGL 3.0}. Promoted to core in {@link GL45 OpenGL 4.5}.</p>
 */
public final class ARBConditionalRenderInverted {

	/** Accepted by the {@code mode} parameter of {@link GL30#glBeginConditionalRender BeginConditionalRender}. */
	public static final int
		GL_QUERY_WAIT_INVERTED              = 0x8E17,
		GL_QUERY_NO_WAIT_INVERTED           = 0x8E18,
		GL_QUERY_BY_REGION_WAIT_INVERTED    = 0x8E19,
		GL_QUERY_BY_REGION_NO_WAIT_INVERTED = 0x8E1A;

	private ARBConditionalRenderInverted() {}

}