// This file is part of Zombiepolis
// Zombiepolis is based on MicropolisJ
// Copyright (C) 2013-2014 Jason Long for MicropolisJ
// Portions Copyright (C) 1989-2007 Electronic Arts Inc.
//
// Zombiepolis is free software; you can redistribute it and/or modify
// it under the terms of the GNU GPLv3, with additional terms.
// See the README file, included in this distribution, for details.

package micropolisj.util;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

public class TranslatedStringsTable extends JTable
{
	public TranslatedStringsTable(TableModel tm)
	{
		super(tm);
		setDefaultEditor(String.class, new DefaultCellEditor(new JTextField()));
	}
}
