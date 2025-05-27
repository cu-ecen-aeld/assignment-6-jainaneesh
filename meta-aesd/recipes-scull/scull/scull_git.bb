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
LIC_FILES_CHKSUM = "file://LICENSE;md5=f098732a73b5f6f3430472f5b094ffdb"

SRC_URI = "git://git@github.com/cu-ecen-aeld/assignment-7-jainaneesh.git;protocol=ssh;branch=master \
           file://0001-Added-support-for-scull-module.patch \
	   file://scull-init \
           "

# Modify these as desired
PV = "1.0+git${SRCPV}"
SRCREV = "97e43abb15e77f7a32c8e48d41a19a9f29d362db"

S = "${WORKDIR}/git"

inherit module update-rc.d

EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull"

FILES:${PN} += "etc/init.d/scull-init"
INITSCRIPT_NAME = "scull-init"
INITSCRIPT_PARAMS = "defaults"
