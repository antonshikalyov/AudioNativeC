#cmake_minimum_required(VERSION 3.22.1)
#
#project("audionativec")
#include_directories(FFmpeg)
#add_library(${CMAKE_PROJECT_NAME} SHARED
#        # List C/C++ source files with relative paths to this CMakeLists.txt.
#        native-lib.cpp
#        FFmpeg/libavfilter/avfilter.c
#        FFmpeg/libavutil/avassert.h
#        )
#target_link_libraries(${CMAKE_PROJECT_NAME}
#        # List libraries link to the target library
#        android
#        log)
#

set(IMPORT_DIR ${CMAKE_SOURCE_DIR}/FFmpeg)

# FFmpeg include file
include_directories(${IMPORT_DIR}/${ANDROID_ABI}/FFmpeg)
# Codec library
add_library(
        avcodec
        SHARED
        IMPORTED
)
set_target_properties(
        avcodec
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libavcodec.so
)
# The filter library is temporarily out of use
add_library(
        avfilter
        SHARED
        IMPORTED
)
set_target_properties(
        avfilter
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libavfilter.so
)

# File format libraries are required for most operations
add_library(
        avformat
        SHARED
        IMPORTED
)

set_target_properties(
        avformat
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libavformat.so
)

# Tool library
add_library(
        avutil
        SHARED
        IMPORTED
)
set_target_properties(
        avutil
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libavutil.so
)

# The resampling library is mainly used for audio conversion.
add_library(
        swresample
        SHARED
        IMPORTED
)
set_target_properties(
        swresample
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libswresample.so
)

# Video format conversion library is mainly used for video conversion.
add_library(
        swscale
        SHARED
        IMPORTED
)
set_target_properties(
        swscale
        PROPERTIES IMPORTED_LOCATION
        ${IMPORT_DIR}/${ANDROID_ABI}/libswscale.so
)


# The main android library, native window, requires this library
target_link_libraries(
        <Your-Native-Library>
        ${log-lib}
        android
        avcodec
        avfilter
        avformat
        avutil
        swresample
        swscale
)