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
FILESEXTRAPATHS:prepend := "${THISDIR}/files"
LIC_FILES_CHKSUM = "file://LICENSE;md5=801f80980d171dd6425610833a22dbe6"
SRC_URI = "file://Makefile \
	   file://access_ok_version.h \
	   file://proc_ops_version.h \
	   file://access.c \
	   file://main.c \
	   file://pipe.c \
	   file://scull.h \
	   file://LICENSE \
	   file://scull-init \
           "

# Modify these as desired
#PV = "1.0+git${SRCPV}"
#SRCREV = "97e43abb15e77f7a32c8e48d41a19a9f29d362db"
SRCREV = "${AUTOREV}"

#S = "${WORKDIR}/git"
S = "${WORKDIR}"

#inherit module update-rc.d
EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} O=${STAGING_KERNEL_BUILDDIR} M=${S} \
			ARCH=${TARGET_ARCH} CROSS_COMPILE=${TARGET_PREFIX}"

do_configure() {
	echo "Preparing kernel build directory at ${STAGING_KERNEL_BUILDDIR}"
	mkdir -p ${STAGING_KERNEL_BUILDDIR}
	oe_runmake -C ${STAGING_KERNLE_DIR} \
			O=${STAGING_KERNEL_BUILDDIR} \
			ARCH=${TARGET_ARCH} \
			CROSS_COMPILE=${TARGET_PREFIX} \
			modules_prepare
}
do_install:append() {
    # Install the kernel module
     install -d ${D}/lib/modules/${KERNEL_VERSION}/extra
     install -m 0644 ${S}/scull.ko ${D}/lib/modules/${KERNEL_VERSION}/extra/

    # Install init script
     install -d ${D}${sysconfdir}/init.d
     install -m 0755 ${WORKDIR}/scull-init ${D}${sysconfdir}/init.d/
}

FILES:${PN} += "/lib/modules"
RDEPENDS:${PN} += "kernel-module-scull"

INITSCRIPT_NAME = "scull-init"
INITSCRIPT_PARAMS = "defaults"

# ✅ Fix postinstall error on host by running only on target
pkg_postinst_ontarget:${PN} () {
     if [ -n "$D" ]; then
              exit 0
     fi

     echo "Running depmod on target..."
     depmod || true
}
#EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}/scull"
#EXTRA_OEMAKE += " -C ${STAGING_KERNEL_DIR} M=${S}"
#FILES:${PN} += "etc/init.d/scull-init"
#INITSCRIPT_NAME = "scull-init"
#INITSCRIPT_PARAMS = "defaults"
