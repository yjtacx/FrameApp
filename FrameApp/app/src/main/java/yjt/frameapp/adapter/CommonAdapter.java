package yjt.frameapp.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * 万能适配器
 * 实现类只需实现其convertview方法
 * @author yujiangtao
 *
 * @param <T>
 */
public abstract class CommonAdapter<T> extends BaseAdapter
{
	protected LayoutInflater mInflater;
	protected Context mContext;
	protected List<T> mDatas;
	protected final int mItemLayoutId;
	private boolean EDIT_STATUS=false;
	private int mPosition=0;

	public CommonAdapter(Context context, List<T> mDatas, int itemLayoutId)
	{
		this.mContext = context;
		this.mInflater = LayoutInflater.from(mContext);
		this.mDatas = mDatas;
		this.mItemLayoutId = itemLayoutId;
	}
	public void setEditStatus(boolean b){
		EDIT_STATUS=b;
		notifyDataSetChanged();
	}

	public void setData(List<T> mDatas){
		this.mDatas=mDatas;
		notifyDataSetChanged();
	}
	@Override
	public int getCount()
	{
		return mDatas==null ? 0 :mDatas.size();
	}

	@Override
	public T getItem(int position)
	{
		return mDatas.get(position);
	}

	@Override
	public long getItemId(int position)
	{
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent)
	{
		final ViewHolder viewHolder = getViewHolder(position, convertView,
				parent);
		mPosition=position;
		convert(viewHolder, getItem(position));
		return viewHolder.getConvertView();
	}
	public int getConvertPosition(){
		return mPosition;
	}

	public abstract void convert(ViewHolder helper, T item);

	private ViewHolder getViewHolder(int position, View convertView,
									 ViewGroup parent)
	{
		return ViewHolder.get(mContext, convertView, parent, mItemLayoutId,
				position);
	}

}
