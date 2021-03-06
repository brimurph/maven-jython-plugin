/**
 * Copyright 2006 - 2007 Servprise International, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.servprise.plugins.jython;

import java.io.File;
import java.util.List;

/**
 * Maven mojo to compile jython test source files to Java test class files.
 * 
 * @author <a href="mailto:kmenard@servprise.com">Kevin Menard</a>
 *
 * @phase test-compile
 * @goal testCompile
 */
public class TestCompilerMojo extends AbstractCompilerMojo
{
    // TODO: (KJM 12/3/06) Check that the expression mirrors that of the compile mojo.
    /**
     *  Destination directory for Java classes (ignoring their package names).
     *
     * @parameter expression="${jython.testSourceDir}" default-value="${project.build.testSourceDirectory}/../jython/"
     */
    private File sourceDir;
    
    /**
     * The directory where compiled test classes go.
     *
     * @parameter expression="${project.build.testOutputDirectory}"
     * @required
     * @readonly
     */
    private File destDir;

    
    public List getSourceDirectories() throws Exception
    {
        List<String> sources = project.getTestCompileSourceRoots();
        //Quick fix in case the user has not added the "add-source" goal.
        String jythonSourceDir = sourceDir.getCanonicalPath();
        if(!sources.contains(jythonSourceDir)) {
            sources.add(jythonSourceDir);
        }
        return sources;
    }

    public File getDestDir()
    {
        return destDir;
    }

}
