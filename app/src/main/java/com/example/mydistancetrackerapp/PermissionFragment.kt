package com.example.mydistancetrackerapp

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.mydistancetrackerapp.util.Permissions.hasLocationPermission
import com.example.mydistancetrackerapp.util.Permissions.requestLocationPermission
import com.example.mydistancetrackerapp.databinding.FragmentPermissionBinding
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog


class PermissionFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private var _binding: FragmentPermissionBinding? = null
    private val binding: FragmentPermissionBinding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentPermissionBinding.inflate(inflater, container, false)

        binding.continueButton.setOnClickListener {
            if(hasLocationPermission(requireContext())) {
                findNavController().navigate( R.id.action_permissionFragment_to_mapsFragment)
            } else {
                requestLocationPermission(this)
            }
        }
        return binding.root
    }



    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions,
            grantResults, this)
    }



    // 최초 실행이
    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
        // 이부분은 계속 실행이 되고
        if( EasyPermissions.somePermissionPermanentlyDenied(this,
                listOf(perms[0]) )) {
            SettingsDialog.Builder(requireActivity()).build().show()
            //Toast.makeText(requireContext(), "aaaa", Toast.LENGTH_LONG).show()

            // 이부분은 최초 한번만 실행 된다.
        } else {
            Toast.makeText(requireContext(), "xxxxxxx", Toast.LENGTH_SHORT).show()
            requestLocationPermission(this)
            // 이요청에 대해서는 위의 callback 이 오지 않고 그냥 넘어간다..
        }
    }

    // 첫번째만 실행이 된다.
    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        findNavController().navigate( R.id.action_permissionFragment_to_mapsFragment )
    }


    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}