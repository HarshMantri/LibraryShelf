package com.wizardSphinx.libraryshelf;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.transition.TransitionManager;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import static com.wizardSphinx.libraryshelf.BookDetailActivity.BOOK_ID;

public class BookRecViewAdapter extends RecyclerView.Adapter<BookRecViewAdapter.ViewHolder> {

    private ArrayList<Book> books = new ArrayList<>();
    private Context mContext;
    private String activityCaller;


    private final String TAG = "bookRecView";

    public BookRecViewAdapter(Context mContext, String activityCaller) {
        this.mContext = mContext;
        this.activityCaller = activityCaller;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        holder.bookName.setText(books.get(position).getName());
        Glide.with(mContext)
                .asBitmap()
                .load(books.get(position).getImgUrl())
                .into(holder.bookImg);

        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: started");
                Intent intent = new Intent(mContext, BookDetailActivity.class);
                intent.putExtra(BOOK_ID, books.get(position).getId());
                mContext.startActivity(intent);
            }
        });

        holder.shortDesc.setText(books.get(position).getShortDescription());
        holder.authorName.setText(books.get(position).getAuthor());

        if (books.get(position).getExtended()) {
            Log.d(TAG, "GetExtended True");
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extendedRelativeLayout.setVisibility(View.VISIBLE);
            holder.downBtn.setVisibility(View.GONE);

            switch (activityCaller) {
                case "allBooks":
                    holder.deleteBtn.setVisibility(View.GONE);
                    break;
                case "alreadyRead":
                    Log.d(TAG, "in already read expanded");
                    holder.deleteBtn.setVisibility(View.VISIBLE);
                    holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to remove the book from already read list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Log.d(TAG, "onClick: hmm");
                                    if (Utils.removeFromAlreadyRead(books.get(position))) {
                                        Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            builder.create().show();

                        }
                    });
                    break;
                case "currentlyReading":
                    holder.deleteBtn.setVisibility(View.VISIBLE);
                    holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to remove the book from currently reading list?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Utils.removeFromCurrentlyReading(books.get(position))) {
                                        Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            builder.create().show();
                        }
                    });
                    break;
                case "wishlist":
                    holder.deleteBtn.setVisibility(View.VISIBLE);
                    holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to remove the book from wishlist?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Utils.removeFromWishlist(books.get(position))) {
                                        Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            builder.create().show();
                        }
                    });
                    break;
                case "favourites":
                    holder.deleteBtn.setVisibility(View.VISIBLE);
                    holder.deleteBtn.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                            builder.setMessage("Are you sure you want to remove the book from favourites?");
                            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    if (Utils.removeFromFavourites(books.get(position))) {
                                        Toast.makeText(mContext, "book removed", Toast.LENGTH_SHORT).show();
                                        notifyDataSetChanged();
                                    } else {
                                        Toast.makeText(mContext, "Something went wrong, Please try again", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                }
                            });

                            builder.create().show();
                        }
                    });
                    break;
                default:
                    break;
            }
        } else {
            Log.d(TAG, "onBindViewHolder: getExtended False");
            TransitionManager.beginDelayedTransition(holder.parent);
            holder.extendedRelativeLayout.setVisibility(View.GONE);
            holder.downBtn.setVisibility(View.VISIBLE);
        }


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        CardView parent;
        ImageView bookImg;
        TextView bookName, authorName, shortDesc;
        RelativeLayout relativeLayout, extendedRelativeLayout;
        ImageView upBtn, downBtn;
        TextView deleteBtn;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            parent = itemView.findViewById(R.id.parent);
            bookImg = itemView.findViewById(R.id.imgBook);
            bookName = itemView.findViewById(R.id.txtBook);
            relativeLayout = itemView.findViewById(R.id.rel1);
            extendedRelativeLayout = itemView.findViewById(R.id.extendedRel1);
            authorName = itemView.findViewById(R.id.txtAuthorName);
            shortDesc = itemView.findViewById(R.id.txtShortDesc);
            upBtn = itemView.findViewById(R.id.up_arrow);
            downBtn = itemView.findViewById(R.id.down_arrow);
            deleteBtn = itemView.findViewById(R.id.delete_btn);

            downBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExtended(!book.getExtended());
                    notifyItemChanged(getAdapterPosition());
                }
            });

            upBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Book book = books.get(getAdapterPosition());
                    book.setExtended(!book.getExtended());
                    notifyItemChanged(getAdapterPosition());
                }
            });
        }
    }
}
