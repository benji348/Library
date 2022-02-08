package com.benjicode.benjlibrary.adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.transition.TransitionManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;


import com.benjicode.benjlibrary.books_activities.BookActivity;
import com.benjicode.benjlibrary.books_activities.BookDetails;
import com.benjicode.benjlibrary.R;
import com.benjicode.benjlibrary.utils.Utils;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.benjicode.benjlibrary.books_activities.BookActivity.BOOK_ID_KEY;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    //Declaring varialbes
    private static final String TAG = "BookRecViewAdapter";
    private ArrayList<BookDetails> bookDetails = new ArrayList<>();
    private Context context;
    private String parentActivity;

    public BookRecViewAdapter(Context context, String parentActivity) {
        this.context = context;
        this.parentActivity = parentActivity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_book,parent,false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        Log.d(TAG,"onBindViewHolder: Called");
        holder.txtName.setText(bookDetails.get(position).getName());

        Glide.with(context)
                .asBitmap()
                .load(bookDetails.get(position).getImageUrl())
                .into(holder.imgBook);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(context, BookActivity.class);
                intent.putExtra(BOOK_ID_KEY, bookDetails.get(position).getId());
                context.startActivity(intent);
            }
        });

        holder.txtAuthor.setText(bookDetails.get(position).getAuthor());
        holder.txtDescription.setText(bookDetails.get(position).getShortDesc());

        if(bookDetails.get(position).isExpanded()){
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.expandedLayout.setVisibility(View.VISIBLE);
            holder.downArrow.setVisibility(View.GONE);

            if(parentActivity.equals("allbooks")){
                holder.btnDelete.setVisibility(View.GONE);
            }else if(parentActivity.equals("alreadyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + bookDetails.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromAlreadReadBooks(bookDetails.get(position))){
                                    Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }else if(parentActivity.equals("wantToRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + bookDetails.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromWantToReadBooks(bookDetails.get(position))){
                                    Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }else if(parentActivity.equals("currentlyRead")){
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + bookDetails.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromCurrentlyReadBooks(bookDetails.get(position))){
                                    Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }else {
                holder.btnDelete.setVisibility(View.VISIBLE);
                holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage("Are you sure you want to delete " + bookDetails.get(position).getName() + " ?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                if(Utils.getInstance(context).removeFromFavoriteBooks(bookDetails.get(position))){
                                    Toast.makeText(context, "Book deleted", Toast.LENGTH_SHORT).show();
                                    notifyDataSetChanged();
                                }
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });
                        builder.create().show();
                    }
                });

            }
        }else{
            holder.expandedLayout.setVisibility(View.GONE);
            holder.downArrow.setVisibility(View.VISIBLE);

        }

    }


    @Override
    public int getItemCount() {
        return bookDetails.size();
    }

    public void setBookDetails(ArrayList<BookDetails> bookDetails) {
        this.bookDetails = bookDetails;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private CardView parent;
        private ImageView imgBook;
        private TextView txtName;
        private ImageView downArrow , upArrow;
        private RelativeLayout expandedLayout;
        private TextView txtAuthor, txtDescription,btnDelete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            parent = itemView.findViewById(R.id.parent);
            imgBook = itemView.findViewById(R.id.imgBook2);
            txtName  = itemView.findViewById(R.id.txtBookName);

            downArrow = itemView.findViewById(R.id.btndownarrow);
            upArrow   = itemView.findViewById(R.id.btnuparrow);
            expandedLayout = itemView.findViewById(R.id.expandablerelativeLayout);
            txtAuthor = itemView.findViewById(R.id.authorname);
            txtDescription = itemView.findViewById(R.id.Description);

            btnDelete = itemView.findViewById(R.id.btnDelete);

            downArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookDetails bookDetails = BookRecViewAdapter.this.bookDetails.get(getAdapterPosition());
                    bookDetails.setExpanded(!bookDetails.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upArrow.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    BookDetails bookDetails = BookRecViewAdapter.this.bookDetails.get(getAdapterPosition());
                    bookDetails.setExpanded(!bookDetails.isExpanded());
                    notifyItemChanged(getAdapterPosition());
                }
            });


        }
    }
}
