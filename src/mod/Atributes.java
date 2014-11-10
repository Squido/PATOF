package mod;

public enum Atributes {
	Att		{boolean type (){ return false;	}},
	Hp		{boolean type (){ return false;	}},
	Def		{boolean type (){ return false;	}},
	Cost	{boolean type (){ return false;	}},
	Magic	{boolean type (){ return true;	}},
	Dist	{boolean type (){ return true;	}},
	Counter	{boolean type (){ return true;	}};
	abstract boolean type();
}
