package iview.wsienski.mycv.ui.card;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import it.gmariotti.cardslib.library.internal.Card;
import it.gmariotti.cardslib.library.internal.CardHeader;
import iview.wsienski.mycv.R;
import iview.wsienski.mycv.data.ProjectInfo;
import iview.wsienski.mycv.ui.activity.MainActivity;
import iview.wsienski.mycv.ui.fragment.ProjectDeatilsFragment;
import iview.wsienski.mycv.util.Utils;

/**
 * Created by Witold Sienski on 11.07.2016.
 */
public class SuggestedCard extends Card {

    ProjectInfo projectInfo;

    public SuggestedCard(Context context, int innerLayout, ProjectInfo projectInfo) {
        super(context, innerLayout);
        this.projectInfo=projectInfo;
        init();
    }

    private void init() {

        //Add a header
        MyCardHeader header = new MyCardHeader(getContext(), projectInfo.getTitle());
        addCardHeader(header);
        header.setOtherButtonDrawable(R.drawable.ic_action_globe);
        if(!TextUtils.isEmpty(projectInfo.getLink()))
            setOtherBtn(header);

        //Set click listener
        setOnClickListener(new OnCardClickListener() {
            @Override
            public void onClick(Card card, View view) {
                Toast.makeText(getContext(), "Click listener", Toast.LENGTH_LONG).show();
                if(getContext() instanceof MainActivity){
                    MainActivity mainActivity = (MainActivity)getContext();
                    Bundle bundle = new Bundle();
                    ProjectDeatilsFragment projectDeatilsFragment = new ProjectDeatilsFragment();
                    bundle.putSerializable(ProjectDeatilsFragment.PROJECT_BUNDLE_ID, projectInfo);
                    projectDeatilsFragment.setArguments(bundle);
                    mainActivity.switchFragment(projectDeatilsFragment, true);
                }
            }
        });

    }

    private int getIconId(){
        if(projectInfo.getIco()==1){
            return R.drawable.ico_miboa;
        }else{
            return R.mipmap.ic_launcher;
        }
    }

    private void setOtherBtn(CardHeader header){
        header.setOtherButtonVisible(true);

        //Add a callback
        header.setOtherButtonClickListener(new CardHeader.OnClickCardHeaderOtherButtonListener() {
            @Override
            public void onButtonItemClick(Card card, View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(projectInfo.getLink()));
                if(Utils.checkPerm((Activity)getContext(), Manifest.permission.INTERNET))
                getContext().startActivity(browserIntent);
            }
        });
    }

    @Override
    public void setupInnerViewElements(ViewGroup parent, View view) {

        if (view != null) {
            TextView title = (TextView) view.findViewById(R.id.tv1);
            TextView member = (TextView) view.findViewById(R.id.tv2);
            ImageView imageView = (ImageView) view.findViewById(R.id.imageview);

            if (title != null)
                title.setText(projectInfo.getDesc());

            if (member != null) {
                member.setText(R.string.info_press_details);
            }

            if(imageView!=null)
                imageView.setImageDrawable(getContext().getResources().getDrawable(getIconId()));
        }
    }


}

