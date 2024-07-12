package com.android.wbpo_fake_users.users

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import com.android.wbpo_fake_users.databinding.ActivityUsersListBinding
import com.android.wbpo_fake_users.users.adapters.UsersListRecyclerViewAdapter
import com.android.wbpo_fake_users.users.retrofit.models.User

class UsersListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUsersListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUsersListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val users = listOf(
            User(
                id = 1,
                email = "john.doe@example.com",
                firstName = "John",
                lastName = "Doe",
                avatar = "https://example.com/avatars/johndoe.jpg"
            ),
            User(
                id = 2,
                email = "jane.smith@example.com",
                firstName = "Jane",
                lastName = "Smith",
                avatar = "https://example.com/avatars/janesmith.jpg"
            ),
            User(
                id = 3,
                email = "alice.jones@example.com",
                firstName = "Alice",
                lastName = "Jones",
                avatar = "https://example.com/avatars/alicejones.jpg"
            ),
            User(
                id = 4,
                email = "bob.brown@example.com",
                firstName = "Bob",
                lastName = "Brown",
                avatar = "https://example.com/avatars/bobbrown.jpg"
            ),
            User(
                id = 5,
                email = "carol.wilson@example.com",
                firstName = "Carol",
                lastName = "Wilson",
                avatar = "https://example.com/avatars/carolwilson.jpg"
            ),
            User(
                id = 6,
                email = "dave.davis@example.com",
                firstName = "Dave",
                lastName = "Davis",
                avatar = "https://example.com/avatars/davedavis.jpg"
            ),
            User(
                id = 7,
                email = "eve.evans@example.com",
                firstName = "Eve",
                lastName = "Evans",
                avatar = "https://example.com/avatars/eveevans.jpg"
            ),
            User(
                id = 8,
                email = "frank.thomas@example.com",
                firstName = "Frank",
                lastName = "Thomas",
                avatar = "https://example.com/avatars/frankthomas.jpg"
            ),
            User(
                id = 9,
                email = "grace.moore@example.com",
                firstName = "Grace",
                lastName = "Moore",
                avatar = "https://example.com/avatars/gracemoore.jpg"
            ),
            User(
                id = 10,
                email = "henry.clark@example.com",
                firstName = "Henry",
                lastName = "Clark",
                avatar = "https://example.com/avatars/henryclark.jpg"
            )
        )

        binding.recyclerView.adapter = UsersListRecyclerViewAdapter(users) { item, button ->
            createOnClickListener(
                item,
                button
            )
        }
    }

    private fun createOnClickListener(item: User, button: ImageButton) {
        val onClickListener = View.OnClickListener {


        }

    }
}



