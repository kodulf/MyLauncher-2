package bjpkten.mylauncher;

import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private List<ResolveInfo> mApps;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        initData();
        mRecyclerView.setAdapter(new MyRecyclerViewAdapter());
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,3));


    }

    public void initData(){
        Intent intent = new Intent(Intent.ACTION_VIEW,null);
        intent.addCategory(Intent.CATEGORY_DEFAULT);
        mApps = getPackageManager().queryIntentActivities(intent, 0);
    }


    class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        public MyViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageView);
        }
    }

    class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyViewHolder>{

        @NonNull
        @Override
        public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_icon, null);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
                myViewHolder.imageView.setImageDrawable(mApps.get(i).loadIcon(getPackageManager()));
        }

        @Override
        public int getItemCount() {
            return mApps.size();
        }
    }
}
