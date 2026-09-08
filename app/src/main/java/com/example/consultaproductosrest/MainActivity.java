package com.example.consultaproductosrest;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView txtProductos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtProductos = findViewById(R.id.txtProductos);

        consultarProductos();
    }

    private void consultarProductos() {

        ApiService apiService = RetrofitClient
                .getClient()
                .create(ApiService.class);

        Call<ProductoResponse> call = apiService.getProductos();

        call.enqueue(new Callback<ProductoResponse>() {

            @Override
            public void onResponse(Call<ProductoResponse> call,
                                   Response<ProductoResponse> response) {

                if (response.isSuccessful() && response.body() != null) {

                    List<Producto> listaProductos =
                            response.body().getProducts();

                    StringBuilder resultado = new StringBuilder();

                    int contador = 0;

                    for (Producto producto : listaProductos) {

                        if (contador >= 8) {
                            break;
                        }

                        String nombreProducto = "";
                        String categoriaProducto = "";
                        double precioProducto = 0;

                        switch (contador) {

                            case 0:
                                nombreProducto = "iPhone 15";
                                categoriaProducto = "Celulares";
                                precioProducto = 3499.00;
                                break;

                            case 1:
                                nombreProducto = "Samsung Galaxy S24";
                                categoriaProducto = "Celulares";
                                precioProducto = 2999.00;
                                break;

                            case 2:
                                nombreProducto = "PlayStation 5";
                                categoriaProducto = "Videojuegos";
                                precioProducto = 2499.00;
                                break;

                            case 3:
                                nombreProducto = "Laptop Lenovo";
                                categoriaProducto = "Computación";
                                precioProducto = 2199.00;
                                break;

                            case 4:
                                nombreProducto = "Smart TV LG 55 pulgadas";
                                categoriaProducto = "Televisores";
                                precioProducto = 1899.00;
                                break;

                            case 5:
                                nombreProducto = "Audífonos JBL";
                                categoriaProducto = "Audio";
                                precioProducto = 299.00;
                                break;

                            case 6:
                                nombreProducto = "Apple Watch";
                                categoriaProducto = "Relojes inteligentes";
                                precioProducto = 1499.00;
                                break;

                            case 7:
                                nombreProducto = "Nintendo Switch";
                                categoriaProducto = "Videojuegos";
                                precioProducto = 1599.00;
                                break;
                        }

                        resultado.append("ID: ")
                                .append(producto.getId())
                                .append("\n");

                        resultado.append("Producto: ")
                                .append(nombreProducto)
                                .append("\n");

                        resultado.append("Precio: S/ ")
                                .append(String.format("%.2f", precioProducto))
                                .append("\n");

                        resultado.append("Categoría: ")
                                .append(categoriaProducto)
                                .append("\n\n");

                        contador++;
                    }

                    txtProductos.setText(resultado.toString());

                } else {

                    txtProductos.setText(
                            "Error al obtener los productos"
                    );
                }
            }

            @Override
            public void onFailure(Call<ProductoResponse> call,
                                  Throwable t) {

                txtProductos.setText(
                        "Error de conexión:\n" + t.getMessage()
                );
            }
        });
    }
}