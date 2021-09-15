/*
 * Copyright (C) 2009-2017 Apple Inc. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY APPLE INC. ``AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL APPLE INC. OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY
 * OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

#include "config.h"
#include "WebGLObject.h"

#if ENABLE(WEBGL)

#include "WebGLCompressedTextureS3TC.h"
#include "WebGLContextGroup.h"
#include "WebGLDebugRendererInfo.h"
#include "WebGLDebugShaders.h"
#include "WebGLLoseContext.h"
#include "WebGLRenderingContextBase.h"

namespace WebCore {

void WebGLObject::setObject(Platform3DObject object)
{
    ASSERT(!m_object);
    ASSERT(!m_deleted);
    m_object = object;
}

void WebGLObject::deleteObject(GraphicsContext3D* context3d)
{
    m_deleted = true;
    if (!m_object)
        return;

    if (!hasGroupOrContext())
        return;

    if (!m_attachmentCount) {
        if (!context3d)
            context3d = getAGraphicsContext3D();

        if (context3d)
            deleteObjectImpl(context3d, m_object);

        m_object = 0;
    }
}

void WebGLObject::detach()
{
    m_attachmentCount = 0; // Make sure OpenGL resource is deleted.
}

void WebGLObject::onDetached(GraphicsContext3D* context3d)
{
    if (m_attachmentCount)
        --m_attachmentCount;
    if (m_deleted)
        deleteObject(context3d);
}

}

#endif // ENABLE(WEBGL)
