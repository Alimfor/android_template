package alim.dev.library;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProductDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView imageView = findViewById(R.id.productImageView);
        TextView descriptionTextView = findViewById(R.id.productDescriptionTextView);
        Button backButton = findViewById(R.id.backButton);

        int productIndex = getIntent().getIntExtra("productIndex", 0);
        Product product = ProductsRepository.getProduct(productIndex);

        imageView.setImageResource(product.getImageResId());
        descriptionTextView.setText(product.getDescription());

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
