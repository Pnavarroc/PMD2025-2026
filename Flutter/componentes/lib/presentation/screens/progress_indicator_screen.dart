import 'package:flutter/material.dart';

class ProgressScreen extends StatelessWidget {
  const ProgressScreen({Key? key}) : super(key: key);

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text("Progress & SnackBar")),
      body: Center(
        child: Column(
          children: [
            SizedBox(height: 10),
            CircularProgressIndicator(
              strokeWidth: 2,
              backgroundColor: Colors.black45,
            ),
            Text("Circular ProgressIndicator"),
            SizedBox(height: 20),
            LinearProgressIndicator(backgroundColor: Colors.black12),
            Text("LinearProgressIndicator"),
            SizedBox(height: 20),
            _CircularControlado(),
          ],
        ),
      ),
    );
  }
}

class _CircularControlado extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return Row(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        CircularProgressIndicator(
          value: 0.178,
          strokeWidth: 5,
          backgroundColor: Colors.black26,
        ),
        SizedBox(width: 20),
        Expanded(child: LinearProgressIndicator(value: 0.75)),
      ],
    );
  }
}
