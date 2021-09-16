/*
 * Copyright (C) 2015-2016 Apple Inc. All rights reserved.
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

#if OS(DARWIN)

#import <sandbox.h>

#if USE(APPLE_INTERNAL_SDK)
#import <sandbox/private.h>
#else
enum sandbox_filter_type {
    SANDBOX_FILTER_NONE,
    SANDBOX_FILTER_GLOBAL_NAME = 2,
};

#define SANDBOX_NAMED_EXTERNAL 0x0003
#endif

WTF_EXTERN_C_BEGIN

extern const char *const APP_SANDBOX_READ;
extern const char *const APP_SANDBOX_READ_WRITE;
extern const enum sandbox_filter_type SANDBOX_CHECK_NO_REPORT;

char *sandbox_extension_issue_file(const char *extension_class, const char *path, uint32_t flags);
char *sandbox_extension_issue_generic(const char *extension_class, uint32_t flags);
int sandbox_check(pid_t, const char *operation, enum sandbox_filter_type, ...);
int sandbox_check_by_audit_token(audit_token_t, const char *operation, enum sandbox_filter_type, ...);
int sandbox_container_path_for_pid(pid_t, char *buffer, size_t bufsize);
int sandbox_extension_release(int64_t extension_handle);
int sandbox_init_with_parameters(const char *profile, uint64_t flags, const char *const parameters[], char **errorbuf);
int64_t sandbox_extension_consume(const char *extension_token);

WTF_EXTERN_C_END

#endif // OS(DARWIN)
