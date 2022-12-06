/*
 * Copyright (C) 2019 Apple Inc. All rights reserved.
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

#pragma once

#include "CachePayload.h"
#include "CachedTypes.h"
#include "CodeSpecializationKind.h"
#include <wtf/Variant.h>

namespace JSC {

class CacheUpdate {
public:
    struct GlobalUpdate {
        CachePayload m_payload;
    };

    struct FunctionUpdate {
        ptrdiff_t m_base;
        CodeSpecializationKind m_kind;
        CachedFunctionExecutableMetadata m_metadata;
        CachePayload m_payload;
    };


    CacheUpdate(GlobalUpdate&&);
    CacheUpdate(FunctionUpdate&&);

    CacheUpdate(CacheUpdate&&);
    CacheUpdate& operator=(CacheUpdate&&);

    bool isGlobal() const;
    const GlobalUpdate& asGlobal() const;
    const FunctionUpdate& asFunction() const;

private:
    Variant<GlobalUpdate, FunctionUpdate> m_update;
};

} // namespace JSC
