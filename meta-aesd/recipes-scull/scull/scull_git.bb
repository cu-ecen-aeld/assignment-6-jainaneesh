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
SUMMARY = "scull kernel module"
DESCRIPTION = "Builds the scull kernel module from the ldd3 source tree"
LIC_FILES_CHKSUM = "file://generic_GPLv2;md5=a14fe441b6663e8969e960a52006e395"
FILESEXTRAPATHS:prepend := "${THISDIR}/files"
SRC_URI = "file://Makefile \
	   file://access.c \
	   file://main.c \
	   file://pipe.c \
	   file://scull.h \
	   file://scull-init \
           "

# Modify these as desired
#PV = "1.0+git${SRCPV}"
#SRCREV = "97e43abb15e77f7a32c8e48d41a19a9f29d362db"
SRCREV = "${AUTOREV}"

#S = "${WORKDIR}/git"
S = "${WORKDIR}"

inherit module update-rc.d

EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}"
FILES:${PN} += "etc/init.d/scull-init"
INITSCRIPT_NAME = "scull-init"
INITSCRIPT_PARAMS = "defaults"
