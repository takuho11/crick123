/**
 * @文件名  NumberShopImp.java
 * @Description
 *	 TODO(用一句话描述该文件做什么)
 * @文件编号
 * @创建人         JOSONHu
 * @日期           2015年7月23日 上午10:46:32
 * @修改人
 * @日期
 * @修改内容
 * @version     V1.0
*/

package cn.topcheer.pms2.api.project.entity;


import cn.topcheer.halberd.app.api.utils.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

/**
 * @ClassName NumberShopImp
 * @Description TODO(这里用一句话描述这个类的作用)
 * （详细说明）
 * @author JOSONHu
 * @version [V1.0, 2015年7月23日 上午10:46:32]
 * @see
 * @since
 */

public class SysNumberShopImp
{

	public static  final int MaxNumber = 200000000;
    private NumberCallBacks mNumberCallBacks = null;
	private String s;
    private SysNumberShop numberShop;


	public SysNumberShopImp(SysNumberShop numbershop)
	{
		this.numberShop=numbershop;
	}


	public NumberCallBacks getNumberCallBacks()
    {
    	StringBuffer s, ss, s1;
	    // StringBuffer sb;
        NumberCallBack nc;
        if (mNumberCallBacks == null)
        {
            mNumberCallBacks = new NumberCallBacks();
            if (this.numberShop.getNumbercallbackstr()  != null&&this.numberShop.getNumbercallbackstr() !="")
                s =new StringBuffer(this.numberShop.getNumbercallbackstr());
            else
                s = new StringBuffer("1,"+MaxNumber);

            while (s.length()>0)
            {
                ss =new StringBuffer(Util.readWord(s, ";"));
                nc = new NumberCallBack();
                nc.numberShop = this.numberShop;
                s1 =new StringBuffer(Util.readWord(ss, ","));// System.Framework.Function.ReadWord(ref ss, ",");
                nc.setNumberStart(Integer.parseInt(s1.toString()));
                if (ss.length()>0)
                    nc.setNumberEnd( Integer.parseInt(ss.toString()));// wcz 2009-12-01
                else
                    nc.setNumberEnd( nc.getNumberStart());
                nc.numberShop = this.numberShop;
                mNumberCallBacks.add(nc);
            }
            //mNumberCallBacks.DoSortProperty("NumberStart", System.ComponentModel.ListSortDirection.Ascending);
            if (mNumberCallBacks.size() > 0)
            {
                nc = mNumberCallBacks.ToArray()[mNumberCallBacks.size() - 1];// as NumberCallBack;
                if (nc.getNumberEnd() < MaxNumber)
                {
                    nc.setNumberEnd ( MaxNumber);
                }
            }
        }
            return mNumberCallBacks;

    }

    public int GetSingleFlowID()
    {
        ArrayList al = this.GetMutiFlowID(1);
        if (al.size()  > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public ArrayList GetMutiFlowID(int NumLen)
    {
        ArrayList rv;
        //DoLock();
        rv = this.getNumberCallBacks().GetNumber(NumLen, false);
        //DoUnlock();
        return rv;
    }
    public void AddCallBack(Object Number)
    {
        //DoLock();
        CallBack(Number);
        //DoUnlock();
    }

    public void AddSkip(Object Number)
    {
        //DoLock();
        Skip(Number);
        //DoUnlock();
    }

    public int PeekSingle()
    {
        ArrayList al = Peek(1);
        if (al.size() > 0)
            return Integer.valueOf(al.get(0).toString());
        else
            return 0;
    }

    public ArrayList Peek(int NumLen)
    {
        ArrayList rv;
        //DoLock();
        rv = this.getNumberCallBacks().GetNumber(NumLen, true);
        //DoUnlock();
        return rv;
    }

    public boolean Contains(int Num)
    {
        while(getNumberCallBacks().iterator().hasNext())//foreach (NumberCallBack nc in numberCallBacks)
        {
            NumberCallBack nc =getNumberCallBacks().iterator().next();
            if (nc.Contains(Num))
                return true;
        }
        return false;
    }


    public void Skip(Object Number)
    {
        String[] arr;

        StringBuffer s1, s2;
        int mStart, mEnd;

        if (Number  instanceof Integer || Number instanceof Long   || Number instanceof Byte)
        {
            Merger(Integer.valueOf(Number.toString()), Integer.valueOf(Number.toString()), false);
        }
        else if (Number instanceof int[])
        {
            for (int i : (int[])Number)
            {
                Merger(i, i, false);
            }
        }
        else if (Number instanceof String)
        {
            arr = ((String)Number).split(",");
            for(String s : arr)
            {
                s1 =new StringBuffer(s);
                s2 =new StringBuffer( Util.readWord(s1, "-"));
                mStart = Integer.parseInt(s2.toString());
                if (s1.length()==0)
                {
                    mEnd = mStart;
                }
                else
                    mEnd = Integer.parseInt(s1.toString());
                if (mStart <= mEnd)
                {
                    Merger(mStart, mEnd, false);
                }
            }
        }
    }

    public void CallBack(Object Number)
    {
        String[] arr;
        StringBuffer s1, s2;
        int mStart, mEnd;

        if (Number instanceof Integer || Number instanceof Long ||  Number instanceof Byte)
        {
            Merger(Integer.valueOf(Number.toString()), Integer.valueOf(Number.toString()), true);
        }
        else if (Number instanceof int[])
        {
            for (int i : (int[])Number)
            {
                Merger(i, i, true);
            }
        }
        else if (Number instanceof String)
        {
            arr = ((String)Number).split(",");
            for(String s : arr)
            {
                s1 =new StringBuffer(s);
                s2 =new StringBuffer(Util.readWord(s1, "-"));
                mStart = Integer.parseInt(s2.toString());
                if (s1.length()==0)
                {
                    mEnd = mStart;
                }
                else
                    mEnd = Integer.parseInt(s1.toString());
                if (mStart <= mEnd)
                {
                    Merger(mStart, mEnd, true);
                }
            }
        }
    }

    private void Merger(int mStart, int mEnd, boolean IsCallBack)
    {
        boolean B = false;
        NumberCallBack CB = null;
        NumberCallBack[] ncs=getNumberCallBacks().ToArray();
        if (IsCallBack)		//回收
        {
            for(int k=0;k<ncs.length ;k++)//foreach (NumberCallBack nc in this.numberCallBacks)
            {
                NumberCallBack nc =  getNumberCallBacks().ToArray()[k];// .ItemAt(k) as NumberCallBack;
                if (nc.Superpose(mStart, mEnd, !B))
                {
                    B = true;
                }
            }
            if (!B)
            {
                AddOneCallBack(mStart, mEnd);
            }
        }
        else				//预留
        {
            int c = this.getNumberCallBacks().size();
            int i = 0;
            for(int j=0;j< ncs.length;j++)//foreach (NumberCallBack nc in this.numberCallBacks)
            {
                NumberCallBack nc = ncs[j];// .ItemAt(j) as NumberCallBack;
                if (i < c)
                {
                    nc.OffSet(mStart, mEnd, getNumberCallBacks(), true);
                    i++;
                }
            }
        }
        //this.numberCallBacks.DoSortProperty("NumberStart", System.ComponentModel.ListSortDirection.Ascending);

        for (int l = 0; l < ncs.length; l++) //foreach (NumberCallBack nc in this.numberCallBacks)
        {
            NumberCallBack nc =ncs[l];
            if (nc.getNumberEnd() >= nc.getNumberStart())
            {
                if (CB != null)
                {
                    if (CB.getNumberEnd() >= nc.getNumberStart() - 1)
                    {
                        if (CB.getNumberEnd() < nc.getNumberEnd())
                        {
                            CB.setNumberEnd(nc.getNumberEnd());
                        }
                        this.getNumberCallBacks().remove(nc);
                    }
                    else
                        CB = nc;
                }
                else
                    CB = nc;
            }
            else
            	getNumberCallBacks().remove(nc);
        }

    }

    private NumberCallBack AddOneCallBack(int numberStart, int numberEnd)
    {
        NumberCallBack nc = new NumberCallBack();
        nc.setmNumberStart(numberStart);
        nc.setNumberEnd( numberEnd);
        nc.numberShop = this.numberShop;
        this.getNumberCallBacks().add(nc);
        return nc;
    }


    public SysNumberShop getNumberShop() {
        return numberShop;
    }

    public void setNumberShop(SysNumberShop numberShop) {
        this.numberShop = numberShop;
    }

    public class NumberCallBack
    {
        public NumberCallBack()
        {
        	this.Key=Util.NewGuid();
        }
        public String Key;



        private int mNumberStart, mNumberEnd;
        public int getmNumberStart() {
			return mNumberStart;
		}
		public void setmNumberStart(int mNumberStart) {
			this.mNumberStart = mNumberStart;
		}
		public int getmNumberEnd() {
			return mNumberEnd;
		}
		public void setmNumberEnd(int mNumberEnd) {
			this.mNumberEnd = mNumberEnd;
		}
		public int getNumberEnd() {
			return mNumberEnd;
		}
		public void setNumberEnd(int numberEnd) {
			mNumberEnd = numberEnd;
		}
		public int getNumberStart() {
			return mNumberStart;
		}
		public void setNumberStart(int value) {
			mNumberStart = value;
		}
		public int getNumberLength() {
			return this.getNumberEnd()-this.getNumberStart()+1;
		}


		public SysNumberShop numberShop;
        public String getName()
        {
        	if(numberShop==null)
        		return "";
        	else
        		return this.numberShop.getName();
        }


        public boolean Contains(int Num)
        {
            if (this.getNumberStart() <= Num && this.getNumberEnd() > Num)
                return true;
            else
                return false;
        }

        public boolean Superpose(int mStart, int mEnd, boolean SetValue)
        {
            boolean rv = false;
            if (Between(this.getNumberStart(), this.getNumberEnd() + 1, mStart) || Between(this.getNumberStart() - 1, this.getNumberEnd(), mEnd))
            {
                rv = true;
            }
            else if (this.getNumberStart() > mStart && this.getNumberEnd() < mEnd)
            {
                rv = true;
            }
            if (rv && SetValue)
            {
                this.setNumberEnd((mEnd > this.getNumberEnd() ? mEnd : this.getNumberEnd()));
                this.setNumberStart( (mStart < this.getNumberStart() ? mStart : this.getNumberStart()));
            }
            return rv;
        }

        public void OffSet(int mStart, int mEnd, NumberCallBacks numberCallBacks, boolean SetValue)
        {
            NumberCallBack mCb;
            if (Between(this.getNumberStart(), this.getNumberEnd(), mStart))
            {
                if (Between(this.getNumberStart(), this.getNumberEnd(), mEnd))
                {
                    if (SetValue)
                    {
                        if (this.getNumberEnd() < mEnd + 1)
                            numberCallBacks.remove(this);
                        else
                        {
                            mCb = new NumberCallBack();
                            mCb.setNumberStart(mEnd + 1);
                            mCb.setNumberEnd(this.getNumberEnd());
                            mCb.numberShop = this.numberShop;
                            numberCallBacks.add( mCb);
                            this.setNumberEnd(mStart - 1);
                        }
                    }
                }
                else
                {
                    if (SetValue)
                    {
                        this.setNumberEnd(mStart - 1);
                    }
                }
            }
            else
            {
                if (Between(this.getNumberStart(), this.getNumberEnd(), mEnd))
                {
                    if (SetValue)
                    {
                        this.setNumberStart(mEnd + 1);
                    }
                }
                else if (mEnd > this.getNumberEnd() && mStart < this.getNumberStart())
                {
                    if (SetValue)
                    {
                        numberCallBacks.remove(this);
                    }
                }

            }
        }

        private boolean Between(int mStart, int mEnd, int Number)
        {
            if (mStart <= Number && mEnd >= Number)
                return true;
            else
                return false;
        }

    }

	public class NumberCallBacks  extends  TreeSet<NumberCallBack>
    {
        //public String mSortProperty;
        public NumberCallBacks()
        {

           super(new CollatorComparator());
        }

        public NumberCallBack[] ToArray()
        {
        	NumberCallBack[] nbk=new NumberCallBack[1];
        	return  super.toArray(nbk);
        }

        public ArrayList GetNumber(int Length, boolean IsPeek)
        {
            ArrayList rv;
            rv = new ArrayList();
            NumberCallBack[] arrayNumberCallBack=(NumberCallBack[])this.ToArray();
            NumberCallBack mNumberCallBack;
            int mType;
            int mLen;
            if (arrayNumberCallBack.length > 0)
            {
                mNumberCallBack =arrayNumberCallBack[0];// as NumberCallBack;
                mType = 1;  //mNumberCallBack.numberShop.getNumbertype();
                mLen = Length;
                switch (mType)
                {
                    case  1://numbershop.nsContinuumPreference:
                        for(int m=0;m<arrayNumberCallBack.length;m++)//foreach (NumberCallBack nc in this)
                        {
                            NumberCallBack nc = arrayNumberCallBack[m];// as NumberCallBack;
                            if (mLen == 0)
                                return rv;
                            else
                            {
                                if (mLen >= nc.getNumberLength())
                                {
                                    mLen = mLen - nc.getNumberLength();
                                    for (int i = nc.getNumberStart(); i <= nc.getNumberEnd(); i++)
                                    {
                                        rv.add(i);
                                    }
                                    if (!IsPeek)
                                    {
                                        this.remove(nc);
                                    }
                                }
                                else
                                {
                                    for (int i = nc.getNumberStart(); i <= nc.getNumberStart() + mLen - 1; i++)
                                    {
                                        rv.add(i);
                                    }
                                    if (!IsPeek)
                                    {
                                        nc.setNumberStart( nc.getNumberStart() + mLen);
                                    }
                                    mLen = 0;
                                }
                            }
                        }
                        break;

                    case 2://numbershop.nsMinPreference  :

                        for(int n=0;n<arrayNumberCallBack.length ;n++)//foreach (NumberCallBack nc in this)
                        {
                            NumberCallBack nc = arrayNumberCallBack[n];// as NumberCallBack;
                            if (nc.getNumberLength() >= Length)
                            {
                                for (int i = nc.getNumberStart(); i <= nc.getNumberStart() + Length - 1; i++)
                                {
                                    rv.add(i);
                                }
                                if (!IsPeek)
                                {
                                    if (nc.getNumberEnd() < nc.getNumberStart() + Length)
                                    {
                                        this.remove(nc);
                                    }
                                    else
                                    {
                                        nc.setNumberStart(  nc.getNumberStart() + Length);
                                    }
                                }
                                break;
                            }
                        }
                        break;
                }

            }
            return rv;

        }
    }

    public class CollatorComparator implements Comparator {


		public int compare(Object o1, Object o2) {
			// TODO Auto-generated method stub


			if(((NumberCallBack)o1).getNumberStart()<((NumberCallBack)o2).getNumberStart())
				return -1;
			else
			{
				if(((NumberCallBack)o1).getNumberStart()==((NumberCallBack)o2).getNumberStart())
					return 0;
				else
					return 1;
			}
			}
		}

	/**
	 * @param numberName
	 */
	public void setName(String numberName)
	{
		this.numberShop.setName(numberName);
	}


	/**
	 * @param s2
	 */
	public void setNumbercallbackstr(String s2)
	{
		this.numberShop.setNumbercallbackstr(s2);
	}

}
