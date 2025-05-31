# Recipe created by recipetool
# This is the basis of a recipe and may need further editing in order to be fully functional.
# (Feel free to remove these comments when editing.)

# WARNING: the following LICENSE and LIC_FILES_CHKSUM values are best guesses - it is
# your responsibility to verify that the values are complete and correct.
#
# The following license files were not able to be identified and are
# represented as "Unknown" below, you will need to check them yourself:
#   LICENSE
LICENSE = "GPLv2"
SUMMARY = "misc-modules kernel driver"
DESCRIPTION = "Builds the misc-modules from the ldd3 source tree"
PN = "misc-modules"
FILESEXTRAPATHS:prepend := "${THISDIR}/files:"
LIC_FILES_CHKSUM = "file://generic_GPLv2;md5=801f80980d171dd6425610833a22dbe6"

SRC_URI = "file://Makefile \
	   file://hello.c \
	   file://hellop.c \
	   file://seq.c \
	   file://jiq.c \
	   file://sleepy.c \
	   file://complete.c \
	   file://silly.c \
	   file://faulty.c \
	   file://kdatasize.c \
           file://kdataalign.c \
           file://jit.c \
	   file://proc_ops_version.h \
	   file://misc-init \
	   file://generic_GPLv2"

# Modify these as desired
#PV = "1.0+git${SRCPV}"
#SRCREV = "97e43abb15e77f7a32c8e48d41a19a9f29d362db"
#SRCREV = "${AUTOREV}"

#S = "${WORKDIR}/git"
S = "${WORKDIR}"

inherit module update-rc.d

#EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/misc-modules"
EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}"

FILES:${PN} += "etc/init.d/misc-init"
INITSCRIPT_NAME = "misc-init"
INITSCRIPT_PARAMS = "defaults"
