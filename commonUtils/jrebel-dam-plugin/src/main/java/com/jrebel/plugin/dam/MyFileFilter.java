package com.jrebel.plugin.dam;

import java.io.File;
import java.io.FileFilter;

public class MyFileFilter implements FileFilter
{
    public boolean accept(final File file)
    {
      return (file.isFile()) && (file.getName().endsWith(".xml"));
    }
  }
