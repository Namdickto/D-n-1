package com.example.duan1.Adapter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duan1.ChatActivity;
import com.example.duan1.Models.Message;
import com.example.duan1.R;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private static final int VIEW_TYPE_USER = 1;
    private static final int VIEW_TYPE_ADMIN = 2;

    private List<Message> messageList;
    private String currentUserEmail = "namvu@gmail.com";
    private String adminEmail = "admin@gmail.com"; // Đảm bảo rằng email admin đúng
    private Context context;

    public ChatAdapter(List<Message> messageList, String currentUserEmail, Context context) {
        this.messageList = messageList;
        this.currentUserEmail = currentUserEmail;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {

        if (position%2 == 0) {
            return VIEW_TYPE_USER;
        } else {
            return VIEW_TYPE_ADMIN;
        }

    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == VIEW_TYPE_USER) {
            View view = inflater.inflate(R.layout.item_user_message, parent, false);
            return new UserMessageViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.item_admin_message, parent, false);
            return new AdminMessageViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Message message = messageList.get(position);
        if (holder instanceof UserMessageViewHolder) {
            ((UserMessageViewHolder) holder).bind(message);
        } else {
            ((AdminMessageViewHolder) holder).bind(message);
        }
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    class UserMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;

        public UserMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.tvMessage);
        }

        public void bind(Message message) {
            textViewMessage.setText(message.getMessage());
        }
    }

    class AdminMessageViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMessage;

        public AdminMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewMessage = itemView.findViewById(R.id.tvMessage);
        }

        public void bind(Message message) {
            textViewMessage.setText(message.getMessage());
        }
    }
}