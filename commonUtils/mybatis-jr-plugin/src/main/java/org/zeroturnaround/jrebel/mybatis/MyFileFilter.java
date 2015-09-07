package org.zeroturnaround.jrebel.mybatis;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter
{
    @Override
    public boolean accept(final File file)
    {
      return (file.isFile()) && (file.getName().endsWith(".xml"));
    }
  }
