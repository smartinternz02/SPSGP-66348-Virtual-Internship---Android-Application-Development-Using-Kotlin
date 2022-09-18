package singha.com.amit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import singha.com.amit.databinding.FragmentOrderConfirmedBinding


class OrderConfirmedFragment : Fragment() {
    lateinit var binding: FragmentOrderConfirmedBinding



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding= FragmentOrderConfirmedBinding.inflate(inflater, container, false)
        binding.backButton.setOnClickListener {
            Navigation.findNavController(it).navigate(R.id.action_orderConfirmedFragment_to_homeFragment)
        }
        return binding.root
    }

}