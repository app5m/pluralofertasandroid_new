package br.com.app5m.pluralofertas.ui.activity

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import br.com.app5m.pluralofertas.R
import br.com.app5m.pluralofertas.util.Preferences
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kotlin.math.min


class IntroAct : AppCompatActivity(), ViewPager.OnPageChangeListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        Preferences(this).storeInt(Preferences.ENTERING_FIRST_TIME, 0)

        pagerView.adapter = mSectionsPagerAdapter

        introIndicator.setViewPager(pagerView)

        pagerView.addOnPageChangeListener(this)

        next_intro_bt.setOnClickListener {
            nextButtonPressed()
        }

        skip_intro_bt.setOnClickListener {
            finishIntro()

        }

    }

    private fun nextButtonPressed() {
        pagerView.setCurrentItem(min(pagerView.currentItem +1, 4), true)
    }

    private fun finishIntro() {
        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("finishedIntro", true).apply()
        val intent = Intent(this, HomeAct::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //SharedPreferencesManager.putBoolean(PREFS_ONBOARDING,true)
        finishAffinity()
        startActivity(intent)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (position == 2) {
            next_intro_bt.setOnClickListener { finishIntro() }

        } else {

            next_intro_bt.setOnClickListener { nextButtonPressed() }
        }
    }

    inner class SectionsPagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentPagerAdapter(fm) {

        override fun getItem(position: Int): androidx.fragment.app.Fragment {
            return PlaceholderFragment.newInstance(position)
        }

        override fun getCount(): Int {
            return 3
        }
    }

    class PlaceholderFragment : androidx.fragment.app.Fragment() { //seta o layout para o viewPager

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            var rootView = inflater.inflate(R.layout.fragment_intro, container, false)

            when (arguments?.getInt(ARG_SECTION_NUMBER)) {
                0 -> {
                    rootView.onboardingImg.setImageResource(R.drawable.growth)
                    rootView.onboardingTitle.setText("Aproveite as vantagens")
                    rootView.onboardingSubtitle.setText("Veja as promo????es de sua cidade e aproveite os descontos")
                }

                1 -> {
                    rootView.onboardingImg.setImageResource(R.drawable.cart)
                    rootView.onboardingTitle.setText("Compre com Facilidade")
                    rootView.onboardingSubtitle.setText("Com o plural ?? tudo muito f??cil e seguro, com poucos cliques voc?? compra um cupom")

                }

                2 -> {
                    rootView.onboardingImg.setImageResource(R.drawable.smartphone)
                    rootView.onboardingTitle.setText("Na palma da sua m??o")
                    rootView.onboardingSubtitle.setText("Seu cupom com voc??, aonde voc?? estiver, aproveite!")
                }
            }

            return rootView
        }

        companion object {
            /**
             * The fragment argument representing the section number for this
             * fragment.
             */
            private val ARG_SECTION_NUMBER = "section_number"

            /**
             * Returns a new instance of this fragment for the given section
             * number.
             */
            fun newInstance(sectionNumber: Int): PlaceholderFragment {
                val fragment = PlaceholderFragment()
                val args = Bundle()
                args.putInt(ARG_SECTION_NUMBER, sectionNumber)
                fragment.arguments = args
                return fragment
            }
        }
    }
}
