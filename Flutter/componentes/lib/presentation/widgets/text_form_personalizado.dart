import 'package:flutter/material.dart';

class TextFromPersonalizado extends StatelessWidget {
  final String? hintText;
  final String? labelText;
  final String? helperTrext;
  final IconData? icon;
  final IconData? suffixIcon;

  const TextFromPersonalizado({
    super.key,
    this.hintText,
    this.labelText,
    this.helperTrext,
    this.icon,
    this.suffixIcon,
  });
  @override
  Widget build(BuildContext context) {
    return TextFormField(
      // initialValue: 'Pablo N.',
      autofocus: true,
      textCapitalization: TextCapitalization.words,
      onChanged: (value) {
        print(value);
      },
      validator: (value) {
        return value!.length < 5 ? 'Mínimo 5 caracteres' : null;
      },
      autovalidateMode: AutovalidateMode.onUserInteraction,
      decoration: InputDecoration(
        border: OutlineInputBorder(),
        hintText: hintText,
        labelText: labelText,
        helperText: helperTrext,
        //counterText: "3 caracteres",
        suffixIcon: suffixIcon != null ? Icon(suffixIcon) : null,
        //prefixIcon: Icon(Icons.verified_user_outlined),
        icon: icon != null ? Icon(icon) : null,
        focusedBorder: OutlineInputBorder(
          borderRadius: BorderRadius.circular(20),
        ),
      ),
    );
  }
}
