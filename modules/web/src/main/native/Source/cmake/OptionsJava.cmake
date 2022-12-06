include(GNUInstallDirs)

set(WEBKITJAVA_API_VERSION 4.0)

set(ICU_UNICODE TRUE)
SET_AND_EXPOSE_TO_BUILD(HAVE_ACCESSIBILITY OFF)

SET_AND_EXPOSE_TO_BUILD(USE_IMAGEIO TRUE)
SET_AND_EXPOSE_TO_BUILD(USE_TEXTURE_MAPPER TRUE)
SET_AND_EXPOSE_TO_BUILD(USE_NPOBJECT OFF)
SET_AND_EXPOSE_TO_BUILD(ENABLE_JAVA_BRIDGE ON)
SET_AND_EXPOSE_TO_BUILD(ENABLE_JAVA_JSC ON)
if (ICU_UNICODE)
    SET_AND_EXPOSE_TO_BUILD(USE_ICU_UNICODE TRUE)
else ()
    SET_AND_EXPOSE_TO_BUILD(USE_JAVA_UNICODE TRUE)
endif ()

# Eventloop
SET_AND_EXPOSE_TO_BUILD(WTF_DEFAULT_EVENT_LOOP 0)
if (WIN32)
    SET_AND_EXPOSE_TO_BUILD(USE_WINDOWS_EVENT_LOOP 1)
elseif (APPLE)
    SET_AND_EXPOSE_TO_BUILD(USE_COCOA_EVENT_LOOP 1)
elseif (UNIX)
    SET_AND_EXPOSE_TO_BUILD(USE_GENERIC_EVENT_LOOP 1)
endif ()

# These are shared variables, but we special case their definition so that we can use the
# CMAKE_INSTALL_* variables that are populated by the GNUInstallDirs macro.
set(LIB_INSTALL_DIR "${CMAKE_INSTALL_FULL_LIBDIR}" CACHE PATH "Absolute path to library installation directory")
set(EXEC_INSTALL_DIR "${CMAKE_INSTALL_FULL_BINDIR}" CACHE PATH "Absolute path to executable installation directory")

set(SQLite3_LIBRARIES SqliteJava)
set(LIBXML2_LIBRARIES XMLJava)
set(LIBXSLT_LIBRARIES XSLTJava)

set(ICU_LIBRARIES icuuc icudata)
set(ICU_I18N_LIBRARIES icui18n icuuc icudata)
set(ICU_DATA_LIBRARIES icudata)

find_package(JNI REQUIRED)
find_package(Threads REQUIRED)
# find_package(ZLIB REQUIRED)

if (APPLE)
    add_definitions(-DUSE_CF=1)
    add_definitions(-DJSC_OBJC_API_ENABLED=0)

    set(CMAKE_MACOSX_RPATH TRUE)
    set(CMAKE_INSTALL_RPATH "@loader_path/.")
    set(CMAKE_BUILD_WITH_INSTALL_RPATH TRUE)
elseif (UNIX)
    set(CMAKE_SKIP_RPATH TRUE)
endif ()

if (WIN32)
    # Second, for multi-config builds (e.g. msvc)
    foreach (OUTPUTCONFIG ${CMAKE_CONFIGURATION_TYPES})
        string(TOUPPER ${OUTPUTCONFIG} OUTPUTCONFIG)
        set(CMAKE_RUNTIME_OUTPUT_DIRECTORY_${OUTPUTCONFIG} "${CMAKE_BINARY_DIR}/bin")
        set(CMAKE_LIBRARY_OUTPUT_DIRECTORY_${OUTPUTCONFIG} "${CMAKE_BINARY_DIR}/lib")
        set(CMAKE_ARCHIVE_OUTPUT_DIRECTORY_${OUTPUTCONFIG} "${CMAKE_BINARY_DIR}/lib")
    endforeach (OUTPUTCONFIG CMAKE_CONFIGURATION_TYPES)

    add_definitions(-DNOMINMAX -DUNICODE -D_UNICODE -D_WINDOWS -DWINVER=0x601)

    include(OptionsMSVC)

    # If <winsock2.h> is not included before <windows.h> redefinition errors occur
    # unless _WINSOCKAPI_ is defined before <windows.h> is included
    add_definitions(-D_WINSOCKAPI_=)
elseif(APPLE)
    SET_AND_EXPOSE_TO_BUILD(USE_CF ON)
    SET_AND_EXPOSE_TO_BUILD(HAVE_QOS_CLASSES ON)
else()
    set(CMAKE_SHARED_LINKER_FLAGS "${CMAKE_SHARED_LINKER_FLAGS} -Wl,--export-dynamic")
endif()

WEBKIT_OPTION_BEGIN()
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_ACCESSIBILITY PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_CSS_COMPOSITING PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_DRAG_SUPPORT PUBLIC ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_TOUCH_EVENTS PUBLIC OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_VIDEO PUBLIC ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_3D_TRANSFORMS PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_CSS3_TEXT PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_DATALIST_ELEMENT PUBLIC OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_DOWNLOAD_ATTRIBUTE PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_FTPDIR PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_FULLSCREEN_API PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INDEXED_DATABASE PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_COLOR PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_MEDIA_CONTROLS_SCRIPT PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_MHTML PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_NETSCAPE_PLUGIN_API PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_NOTIFICATIONS PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_QUOTA PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_RESOLUTION_MEDIA_QUERY PRIVATE ON)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_WEBGL PRIVATE OFF)

WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_DATE PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_DATETIMELOCAL PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_MONTH PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_TIME PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_INPUT_TYPE_WEEK PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_REMOTE_INSPECTOR PRIVATE OFF)

WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_WEB_AUDIO PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_WEB_CRYPTO PRIVATE OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_PUBLIC_SUFFIX_LIST PRIVATE OFF)

WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_FTL_JIT PUBLIC OFF)
WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_WEBASSEMBLY PRIVATE OFF)

if (WIN32)
    # FIXME: Port bmalloc to Windows. https://bugs.webkit.org/show_bug.cgi?id=143310
    WEBKIT_OPTION_DEFAULT_PORT_VALUE(USE_SYSTEM_MALLOC PRIVATE ON)
    # Disable 32-bit JIT on Windows. https://bugs.webkit.org/show_bug.cgi?id=185989
    if (${WTF_CPU_X86})
        WEBKIT_OPTION_DEFAULT_PORT_VALUE(ENABLE_JIT PUBLIC OFF)
    endif ()
endif ()


# Finalize the value for all options. Do not attempt to use an option before
# this point, and do not attempt to change any option after this point.
WEBKIT_OPTION_END()


set(ENABLE_WEBKIT_LEGACY ON)
set(ENABLE_WEBKIT OFF)
set(ENABLE_WEBINSPECTORUI OFF)
add_definitions(-DBUILDING_JAVA__=1)
add_definitions(-DDATA_DIR="${CMAKE_INSTALL_DATADIR}")
# add_definitions(-DUSE_CROSS_PLATFORM_CONTEXT_MENUS=1)

set(FORWARDING_HEADERS_DIR ${DERIVED_SOURCES_DIR}/ForwardingHeaders)


set(WTF_LIBRARY_TYPE STATIC)
set(JavaScriptCore_LIBRARY_TYPE STATIC)
set(WebCore_LIBRARY_TYPE STATIC)
set(WebCoreTestSupport_LIBRARY_TYPE STATIC)
set(PAL_LIBRARY_TYPE STATIC)


if (CMAKE_MAJOR_VERSION LESS 3)
    # Before CMake 3 it was necessary to use a build script instead of using cmake --build directly
    # to preserve colors and pretty-printing.

    build_command(COMMAND_LINE_TO_BUILD)
    # build_command unconditionally adds -i (ignore errors) for make, and there's
    # no reasonable way to turn that off, so we just replace it with -k, which has
    # the same effect, except that the return code will indicate that an error occurred.
    # See: http://www.cmake.org/cmake/help/v3.0/command/build_command.html
    string(REPLACE " -i" " -k" COMMAND_LINE_TO_BUILD ${COMMAND_LINE_TO_BUILD})
    file(WRITE
        ${CMAKE_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/build.sh
        "#!/bin/sh\n"
        "${COMMAND_LINE_TO_BUILD} $@"
    )
    file(COPY ${CMAKE_BINARY_DIR}${CMAKE_FILES_DIRECTORY}/build.sh
        DESTINATION ${CMAKE_BINARY_DIR}
        FILE_PERMISSIONS OWNER_READ OWNER_WRITE OWNER_EXECUTE GROUP_READ GROUP_EXECUTE
    )
endif ()

