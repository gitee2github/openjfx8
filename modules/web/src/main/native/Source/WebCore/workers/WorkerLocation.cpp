/*
 * Copyright (C) 2008 Apple Inc. All Rights Reserved.
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
#include "WorkerLocation.h"

#include "SecurityOrigin.h"
#include <wtf/text/WTFString.h>

namespace WebCore {

String WorkerLocation::href() const
{
    return m_url.string();
}

String WorkerLocation::protocol() const
{
    return makeString(m_url.protocol(), ":");
}

String WorkerLocation::host() const
{
    return m_url.hostAndPort();
}

String WorkerLocation::hostname() const
{
    return m_url.host();
}

String WorkerLocation::port() const
{
    return m_url.port() ? String::number(m_url.port().value()) : emptyString();
}

String WorkerLocation::pathname() const
{
    return m_url.path().isEmpty() ? "/" : m_url.path();
}

String WorkerLocation::search() const
{
    return m_url.query().isEmpty() ? emptyString() : "?" + m_url.query();
}

String WorkerLocation::hash() const
{
    return m_url.fragmentIdentifier().isEmpty() ? emptyString() : "#" + m_url.fragmentIdentifier();
}

String WorkerLocation::origin() const
{
    return SecurityOrigin::create(m_url)->toString();
}

} // namespace WebCore
