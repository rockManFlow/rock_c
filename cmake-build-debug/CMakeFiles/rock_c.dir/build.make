# CMAKE generated file: DO NOT EDIT!
# Generated by "MinGW Makefiles" Generator, CMake Version 3.17

# Delete rule output on recipe failure.
.DELETE_ON_ERROR:


#=============================================================================
# Special targets provided by cmake.

# Disable implicit rules so canonical targets will work.
.SUFFIXES:


# Disable VCS-based implicit rules.
% : %,v


# Disable VCS-based implicit rules.
% : RCS/%


# Disable VCS-based implicit rules.
% : RCS/%,v


# Disable VCS-based implicit rules.
% : SCCS/s.%


# Disable VCS-based implicit rules.
% : s.%


.SUFFIXES: .hpux_make_needs_suffix_list


# Command-line flag to silence nested $(MAKE).
$(VERBOSE)MAKESILENT = -s

# Suppress display of executed commands.
$(VERBOSE).SILENT:


# A target that is always out of date.
cmake_force:

.PHONY : cmake_force

#=============================================================================
# Set environment variables for the build.

SHELL = cmd.exe

# The CMake executable.
CMAKE_COMMAND = "C:\Program Files\JetBrains\CLion 2020.3\bin\cmake\win\bin\cmake.exe"

# The command to remove a file.
RM = "C:\Program Files\JetBrains\CLion 2020.3\bin\cmake\win\bin\cmake.exe" -E rm -f

# Escaping for special characters.
EQUALS = =

# The top-level source directory on which CMake was run.
CMAKE_SOURCE_DIR = D:\myProjects\rock_c

# The top-level build directory on which CMake was run.
CMAKE_BINARY_DIR = D:\myProjects\rock_c\cmake-build-debug

# Include any dependencies generated for this target.
include CMakeFiles/rock_c.dir/depend.make

# Include the progress variables for this target.
include CMakeFiles/rock_c.dir/progress.make

# Include the compile flags for this target's objects.
include CMakeFiles/rock_c.dir/flags.make

CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj: CMakeFiles/rock_c.dir/flags.make
CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj: CMakeFiles/rock_c.dir/includes_C.rsp
CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj: ../function/ffmpeg/my.c
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --progress-dir=D:\myProjects\rock_c\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_1) "Building C object CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -o CMakeFiles\rock_c.dir\function\ffmpeg\my.c.obj   -c D:\myProjects\rock_c\function\ffmpeg\my.c

CMakeFiles/rock_c.dir/function/ffmpeg/my.c.i: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Preprocessing C source to CMakeFiles/rock_c.dir/function/ffmpeg/my.c.i"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -E D:\myProjects\rock_c\function\ffmpeg\my.c > CMakeFiles\rock_c.dir\function\ffmpeg\my.c.i

CMakeFiles/rock_c.dir/function/ffmpeg/my.c.s: cmake_force
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green "Compiling C source to assembly CMakeFiles/rock_c.dir/function/ffmpeg/my.c.s"
	C:\PROGRA~1\MINGW-~1\X86_64~1.0-P\mingw64\bin\gcc.exe $(C_DEFINES) $(C_INCLUDES) $(C_FLAGS) -S D:\myProjects\rock_c\function\ffmpeg\my.c -o CMakeFiles\rock_c.dir\function\ffmpeg\my.c.s

# Object files for target rock_c
rock_c_OBJECTS = \
"CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj"

# External object files for target rock_c
rock_c_EXTERNAL_OBJECTS =

/Users/opayc/products/rock_c/output/bin/rock_c.exe: CMakeFiles/rock_c.dir/function/ffmpeg/my.c.obj
/Users/opayc/products/rock_c/output/bin/rock_c.exe: CMakeFiles/rock_c.dir/build.make
/Users/opayc/products/rock_c/output/bin/rock_c.exe: CMakeFiles/rock_c.dir/linklibs.rsp
/Users/opayc/products/rock_c/output/bin/rock_c.exe: CMakeFiles/rock_c.dir/objects1.rsp
/Users/opayc/products/rock_c/output/bin/rock_c.exe: CMakeFiles/rock_c.dir/link.txt
	@$(CMAKE_COMMAND) -E cmake_echo_color --switch=$(COLOR) --green --bold --progress-dir=D:\myProjects\rock_c\cmake-build-debug\CMakeFiles --progress-num=$(CMAKE_PROGRESS_2) "Linking C executable \Users\opayc\products\rock_c\output\bin\rock_c.exe"
	$(CMAKE_COMMAND) -E cmake_link_script CMakeFiles\rock_c.dir\link.txt --verbose=$(VERBOSE)

# Rule to build all files generated by this target.
CMakeFiles/rock_c.dir/build: /Users/opayc/products/rock_c/output/bin/rock_c.exe

.PHONY : CMakeFiles/rock_c.dir/build

CMakeFiles/rock_c.dir/clean:
	$(CMAKE_COMMAND) -P CMakeFiles\rock_c.dir\cmake_clean.cmake
.PHONY : CMakeFiles/rock_c.dir/clean

CMakeFiles/rock_c.dir/depend:
	$(CMAKE_COMMAND) -E cmake_depends "MinGW Makefiles" D:\myProjects\rock_c D:\myProjects\rock_c D:\myProjects\rock_c\cmake-build-debug D:\myProjects\rock_c\cmake-build-debug D:\myProjects\rock_c\cmake-build-debug\CMakeFiles\rock_c.dir\DependInfo.cmake --color=$(COLOR)
.PHONY : CMakeFiles/rock_c.dir/depend

