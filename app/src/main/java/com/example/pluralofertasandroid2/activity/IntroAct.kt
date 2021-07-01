package com.example.pluralofertasandroid2.activity

import android.content.Intent
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.pluralofertasandroid2.R
import kotlinx.android.synthetic.main.activity_intro.*
import kotlinx.android.synthetic.main.fragment_intro.view.*
import kotlin.math.min


class IntroAct : AppCompatActivity(), ViewPager.OnPageChangeListener{

    private var mSectionsPagerAdapter: SectionsPagerAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        mSectionsPagerAdapter = SectionsPagerAdapter(supportFragmentManager)

        container.adapter = mSectionsPagerAdapter

        introIndicator.setViewPager(container)

        container.addOnPageChangeListener(this)

        btnProximo.setOnClickListener {
            nextButtonPressed()
        }

        btnPular.setOnClickListener {
            finishIntro()
        }

        btnComecar.setOnClickListener {
            finishIntro()
        }

    }

    private fun nextButtonPressed() {
        container.setCurrentItem(min(container.currentItem +1, 4), true)
    }

    private fun finishIntro() {
        //PreferenceManager.getDefaultSharedPreferences(this).edit().putBoolean("finishedIntro", true).apply()
        //val intent = Intent(this, MainActivity::class.java)
        //intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        //SharedPreferencesManager.putBoolean(PREFS_ONBOARDING,true)
        //startActivity(intent)
    }

    override fun onPageScrollStateChanged(state: Int) {
    }

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {
    }

    override fun onPageSelected(position: Int) {
        if (position == 2) {
            btnProximo.visibility = View.GONE
            btnPular.visibility = View.GONE
            introIndicator.visibility = View.VISIBLE
            btnComecar.setOnClickListener { finishIntro() }
            btnComecar.visibility = View.VISIBLE

        } else {
            btnComecar.visibility = View.GONE
            btnProximo.visibility = View.VISIBLE
            btnPular.visibility = View.VISIBLE
            introIndicator.visibility = View.VISIBLE
            btnProximo.setOnClickListener { nextButtonPressed() }

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
                    rootView.onboardingImg.setImageResource(R.drawable.label)
                    rootView.onboardingTitle.setText("Aproveite as vantagens:")
                    rootView.onboardingSubtitle.setText("Veja as promoções de sua cidade e aproveite os descontos")
                }

                1 -> {
                    rootView.onboardingImg.setImageResource(R.drawable.shopping_cart)
                    rootView.onboardingTitle.setText("Compre com Facilidade:")
                    rootView.onboardingSubtitle.setText("Com o plural é tudo muito fácil e seguro, com poucos cliques você compra um cupom")

                }

                2 -> {
                    rootView.onboardingImg.setImageResource(R.drawable.mobile)
                    rootView.onboardingTitle.setText("Na palma da sua mão:")
                    rootView.onboardingSubtitle.setText("Seu cupom com você, aonde você estiver, aproveite!")
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
