/* -*- Mode: C; tab-width: 2; indent-tabs-mode: t; c-basic-offset: 2 -*- */
/* GStreamer AIFF plugin initialisation
 * Copyright (C) <2008> Pioneers of the Inevitable <songbird@songbirdnest.com>
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Library General Public
 * License as published by the Free Software Foundation; either
 * version 2 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Library General Public License for more details.
 *
 * You should have received a copy of the GNU Library General Public
 * License along with this library; if not, write to the
 * Free Software Foundation, Inc., 51 Franklin St, Fifth Floor,
 * Boston, MA 02110-1301, USA.
 */

#ifdef HAVE_CONFIG_H
#include "config.h"
#endif

#include "aiffelements.h"

#ifdef GSTREAMER_LITE
gboolean
plugin_init_aiff (GstPlugin * plugin)
#else // GSTREAMER_LITE
static gboolean
plugin_init (GstPlugin * plugin)
#endif // GSTREAMER_LITE
{
  gboolean ret = FALSE;

  ret |= GST_ELEMENT_REGISTER (aiffparse, plugin);
#ifndef GSTREAMER_LITE
  ret |= GST_ELEMENT_REGISTER (aiffmux, plugin);
#endif

  return ret;
}

#ifndef GSTREAMER_LITE
GST_PLUGIN_DEFINE (GST_VERSION_MAJOR,
    GST_VERSION_MINOR,
    aiff,
    "Create and parse Audio Interchange File Format (AIFF) files",
    plugin_init, VERSION, GST_LICENSE, GST_PACKAGE_NAME, GST_PACKAGE_ORIGIN)
#endif
