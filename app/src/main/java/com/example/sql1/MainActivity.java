package com.example.sql1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
EditText editRoll,editName,editMarks;
Button btnAdd,btnDelete,btnModify,btnView,btnViewAll,btnShow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editRoll=findViewById(R.id.editRollno);
        editName=findViewById(R.id.editName);
        editMarks=findViewById(R.id.editMark);
        btnAdd=findViewById(R.id.buttonAdd);
        btnModify=findViewById(R.id.buttonModify);
        btnDelete=findViewById(R.id.buttonDelete);
        btnView=findViewById(R.id.btnView);
        btnViewAll=findViewById(R.id.btnViewAll);
        btnShow=findViewById(R.id.btnShow);

        btnAdd.setOnClickListener(this);
        btnModify.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnView.setOnClickListener(this);
        btnViewAll.setOnClickListener(this);
        btnShow.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
     switch (v.getId()){

         case R.id.buttonAdd:
             Toast.makeText(this, "Add", Toast.LENGTH_SHORT).show();
             break;

         case R.id.buttonDelete:
             Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
             break;

         case R.id.buttonModify:
             Toast.makeText(this, "Modify", Toast.LENGTH_SHORT).show();
             break;
         case R.id.btnView:
             Toast.makeText(this, "View", Toast.LENGTH_SHORT).show();
             break;
         case R.id.btnViewAll:
             Toast.makeText(this, "ViewAll", Toast.LENGTH_SHORT).show();
             break;

         case R.id.btnShow:
             Toast.makeText(this, "Show", Toast.LENGTH_SHORT).show();
             break;
     }
    }
}
