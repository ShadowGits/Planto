package com.example.edp_plantsystem


import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.viewpager.widget.ViewPager
import com.firebase.ui.auth.AuthUI
import com.google.android.material.navigation.NavigationView
import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.google.firebase.udacity.friendlychat.FriendlyMessage
import com.google.firebase.udacity.friendlychat.MessageAdapter

class MainActivity : AppCompatActivity(){
    //navigation drawer
    private var mDrawerLayout: DrawerLayout? = null
    private var mActionBarDrawerToggle: ActionBarDrawerToggle? = null
    private var mNavigationView: NavigationView? = null
    private var mActionBar: ActionBar? = null



     var mMessageAdapter: MessageAdapter? = null


    private var mUsername: String? = null



    private val TAG = "UsingThingspeakAPI"
    private val THINGSPEAK_CHANNEL_ID = "907091"
    private val THINGSPEAK_API_KEY = "YZW2L13XJF6M6323" //GARBAGE KEY
    private val THINGSPEAK_API_KEY_STRING = "api_key"
    /* Be sure to use the correct fields for your own app*/
    private val THINGSPEAK_FIELD1 = "field1"
    private val THINGSPEAK_FIELD2 = "field2"
    private val THINGSPEAK_UPDATE_URL = "https://api.thingspeak.com/update?"
    private val THINGSPEAK_CHANNEL_URL = "https://api.thingspeak.com/channels/"
    private val THINGSPEAK_FEEDS_LAST = "/feeds.json/?results=20&"
    private var mFirebaseDatabase:FirebaseDatabase?=null//entry point of firebase
    private var mMessagesDatabaseReference: DatabaseReference?=null
    private var childEventListener: ChildEventListener?=null


    //for authentication
    private var mFirebaseAuth: FirebaseAuth?=null
    private var mFirebaseAuthListener:FirebaseAuth.AuthStateListener?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val viewPager = findViewById<ViewPager>(R.id.pager)
        val pagerAdapter = MainViewFragmentPagerAdapter(supportFragmentManager)

        viewPager.adapter = pagerAdapter

        val tabLayout = findViewById<TabLayout>(R.id.tab_layout)
        tabLayout.setupWithViewPager(viewPager)

        //Authentication
        mFirebaseAuth=FirebaseAuth.getInstance()

        mFirebaseDatabase= FirebaseDatabase.getInstance()
        //mFirebaseAuth=FirebaseAuth.getInstance()


        mMessagesDatabaseReference=mFirebaseDatabase!!.reference.child("messages")

        mUsername = ""









        mFirebaseAuthListener= object: FirebaseAuth.AuthStateListener{
            override fun onAuthStateChanged(p0: FirebaseAuth) {
                var user = p0.currentUser
                if (user!=null)
                {

                    Toast.makeText(this@MainActivity,"Signed In!",Toast.LENGTH_LONG).show()
                    onSignedInInitialise(user.displayName);
                }
                else
                {
                    //SIgn out
                    //onSignedOutCleanup()
                    val providers = arrayListOf(
                            AuthUI.IdpConfig.EmailBuilder().build(),
                            AuthUI.IdpConfig.GoogleBuilder().build())
// Create and launch sign-in intent
                    startActivityForResult(
                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .build(),
                            RC_SIGN_IN)
                }
            }
        }
        mActionBar = supportActionBar
        mActionBar!!.title = "Planto"





        setupDrawerLayout()



        }



    private fun setupDrawerLayout() {
        mDrawerLayout = findViewById<DrawerLayout>(R.id.navigation_drawer)


        mActionBarDrawerToggle = object : ActionBarDrawerToggle(this, mDrawerLayout, R.string.open, R.string.close) {
            override fun onDrawerStateChanged(newState: Int) {

                super.onDrawerStateChanged(newState)
                val isOpened = mDrawerLayout!!.isDrawerOpen(mNavigationView!!)
                val isVisible = mDrawerLayout!!.isDrawerVisible(mNavigationView!!)

                if (!isOpened && !isVisible) {

                    if (newState == DrawerLayout.STATE_IDLE) {
                        restoreActionBar()
                    } else {
                        overrideActionBar()
                    }
                }
            }

            private fun restoreActionBar() {
                supportActionBar!!.title = "Planto"
                mActionBar!!.show()
                supportInvalidateOptionsMenu()
            }

            private fun overrideActionBar() {
                mActionBar!!.hide()
                supportInvalidateOptionsMenu()
            }
        }


        mDrawerLayout!!.addDrawerListener(mActionBarDrawerToggle!!)
        mActionBarDrawerToggle!!.syncState()

        mActionBar!!.setDisplayHomeAsUpEnabled(true)

        mNavigationView = findViewById<NavigationView>(R.id.navigation_drawer_menu)
        mNavigationView!!.setNavigationItemSelectedListener(object : NavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                val id = item.itemId
                when (id) {

                    R.id.navigation_item_2 -> {Toast.makeText(this@MainActivity, "Know Your Plant", Toast.LENGTH_SHORT).show()
                        var intent:Intent=Intent(this@MainActivity,PlantDataActivity::class.java)
                        //intent.putExtra("plantData",data)
                        startActivity(intent)
                    }
                    R.id.navigation_item3->{var intent=Intent(this@MainActivity,ThingsSpeak::class.java)
                    startActivity(intent)}
                    R.id.navigation_item4->{var intent=Intent(this@MainActivity,ThingsSpeakApiDataFetchActiivty::class.java)
                        startActivity(intent)}
                    else -> return true
                }

                return true
            }
        })
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId==R.id.sign_out_menu)
        {
            AuthUI.getInstance().signOut(this@MainActivity)
            return true
            }

        if (mActionBarDrawerToggle!!.onOptionsItemSelected(item)) {
            return true
        }
        return super.onOptionsItemSelected(item)
    }















    companion object {

        private val TAG = "MainActivity"

        const val ANONYMOUS = "anonymous"
        const val DEFAULT_MSG_LENGTH_LIMIT = 1000

        private const val RC_SIGN_IN=1
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode== RC_SIGN_IN)
        {
            if(resultCode== Activity.RESULT_CANCELED)
            {
                Toast.makeText(this@MainActivity,"EXIT",Toast.LENGTH_SHORT).show()
                //finish()

            }
        }

    }

    private fun onSignedOutCleanup() {
        //remove username
        mUsername= ANONYMOUS
//tear down message screen
        mMessageAdapter!!.clear()


        //detach listener
    }



    private fun onSignedInInitialise(displayName: String?) {
        //Give USERNAME
        mUsername=displayName

        //Attach read listener here so data read only after authentication not before coz den it wont be fetched!


    }











    override fun onResume() {
        super.onResume()
        mFirebaseAuth!!.addAuthStateListener(mFirebaseAuthListener!!)

    }

    override fun onPause() {
        super.onPause()
        if(mFirebaseAuth!=null) {
            mFirebaseAuth!!.removeAuthStateListener(mFirebaseAuthListener!!)
        }

    }



}










