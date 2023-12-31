#
# glib-lite Makefile
#
LINK = link.exe

export THIRD_PARTY_DIR = 3rd_party
export GLIB_BASE_DIR = $(THIRD_PARTY_DIR)/glib

FFI_TARGET = libffi.lib
GLIB_TARGET = libglib.lib
GMODULE_TARGET = libmodule.lib
GOBJECT_TARGET = libgobject.lib
GTHREAD_TARGET = libgthread.lib
MODULES = $(FFI_TARGET) $(GLIB_TARGET) $(GMODULE_TARGET) $(GOBJECT_TARGET) $(GTHREAD_TARGET)

export BUILD_DIR = $(shell cygpath -u "$(OUTPUT_DIR)/$(BUILD_TYPE)")
DEF_DIR = ../../../$(GLIB_BASE_DIR)/build/win32/vs100

TARGET = $(BUILD_DIR)/$(BASE_NAME).dll

MANIFEST   = $(shell cygpath -ma "$(BUILD_DIR)/$(BASE_NAME).manifest")
IMPLIB     = $(shell cygpath -ma "$(BUILD_DIR)/$(BASE_NAME).lib")
export PDB = $(shell cygpath -ma "$(BUILD_DIR)/$(BASE_NAME).pdb")

SYSTEM_LIBS = Ws2_32.lib \
              kernel32.lib \
              user32.lib \
              shell32.lib \
              advapi32.lib \
              ole32.lib \
	      Winmm.lib

LDFLAGS = -out:$(shell cygpath -ma $(TARGET)) -nologo -incremental:no -libpath:$(shell cygpath -ma $(BUILD_DIR)) -dll $(SYSTEM_LIBS) \
          -libpath:$(LIBPATH1) \
          -libpath:$(LIBPATH2) \
          -libpath:$(LIBPATH3) \
          -manifest -manifestfile:$(MANIFEST) -manifestuac:"level='asInvoker' uiAccess='false'" \
          -subsystem:windows -tlbid:1 -dynamicbase -nxcompat -errorreport:queue -implib:$(IMPLIB) \
          -def:$(DEF_DIR)/glib-lite.def

ifeq ($(BUILD_TYPE), Release)
    LDFLAGS += -opt:ref -opt:icf
else # debug
    LDFLAGS += -debug -pdb:$(shell cygpath -ma $(PDB))
endif

ifeq ($(ARCH), x32)
    LDFLAGS += -safeseh -MACHINE:x86
else
    LDFLAGS += -MACHINE:x64
endif

LIBS = $(addprefix $(BUILD_DIR)/,$(MODULES))

.PHONY: default list

default: $(TARGET)

$(TARGET): $(LIBS)
	$(LINK) $(LDFLAGS) $(shell cygpath -ma "$(RESOURCE)") $(foreach lib,$(LIBS),$(shell cygpath -ma $(lib)))

$(BUILD_DIR)/$(FFI_TARGET):
	$(MAKE) TARGET=$(BUILD_DIR)/$(FFI_TARGET) -f Makefile.ffi

$(BUILD_DIR)/$(GLIB_TARGET):
	$(MAKE) TARGET=$(BUILD_DIR)/$(GLIB_TARGET) -f Makefile.glib

$(BUILD_DIR)/$(GMODULE_TARGET):
	$(MAKE) TARGET=$(BUILD_DIR)/$(GMODULE_TARGET) -f Makefile.gmodule

$(BUILD_DIR)/$(GOBJECT_TARGET): $(BUILD_DIR)/$(FFI_TARGET)
	$(MAKE) TARGET=$(BUILD_DIR)/$(GOBJECT_TARGET) -f Makefile.gobject

$(BUILD_DIR)/$(GTHREAD_TARGET):
	$(MAKE) TARGET=$(BUILD_DIR)/$(GTHREAD_TARGET) -f Makefile.gthread
