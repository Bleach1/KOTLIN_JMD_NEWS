# For more information about using CMake with Android Studio, read the
# documentation: https://d.android.com/studio/projects/add-native-code.html

# Sets the minimum version of CMake required to build the native library.
#指定CMake的最小版本
cmake_minimum_required(VERSION 3.4.1)

# Creates and names a library, sets it as either STATIC
# or SHARED, and provides the relative paths to its source code.
# You can define multiple libraries, and CMake builds them for you.
# Gradle automatically packages shared libraries with your APK.
#添加我们自己的模块，名字是native-lib，SHARED可分享的，以及配置源文件或者头文件的路径
add_library( # Sets the name of the library.
             native-lib

             # Sets the library as a shared library.
             SHARED

             # Provides a relative path to your source file(s).
             native-lib.cpp )

# 查找cpp目录下的所有源文件
# 并将名称保存到 DIR_LIB_SRCS 变量
#aux_source_directory(src/main/cpp/ DIR_LIB_SRCS)
# 生成链接库
#add_library (native-lib SHARED ${DIR_LIB_SRCS})
#有多少依次写
#add_library (native-lib SHARED src/main/cpp/native-lib.cpp src/main/cpp/native-lib.cpp)
# 导入cpp目录下的所有头文件
#include_directories(src/main/cpp/)

# Searches for a specified prebuilt library and stores the path as a
# variable. Because CMake includes system libraries in the search path by
# default, you only need to specify the name of the public NDK library
# you want to add. CMake verifies that the library exists before
# completing its build.
#找到log这个模块
find_library( # Sets the name of the path variable.
              log-lib

              # Specifies the name of the NDK library that
              # you want CMake to locate.
              log )

# Specifies libraries CMake should link to your target library. You
# can link multiple libraries, such as libraries you define in this
# build script, prebuilt third-party libraries, or system libraries.
#把我们自己的模块和log模块关联起来
target_link_libraries( # Specifies the target library.
                       native-lib

                       # Links the target library to the log library
                       # included in the NDK.
                       ${log-lib} )